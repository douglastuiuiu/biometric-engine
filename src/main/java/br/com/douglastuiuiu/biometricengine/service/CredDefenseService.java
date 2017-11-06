package br.com.douglastuiuiu.biometricengine.service;

import br.com.douglastuiuiu.biometricengine.exception.*;
import br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.request.entity.credit.CreditDocumentDTO;
import br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.request.entity.uploaddocument.UploadDocumentsDocumentDTO;
import br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.response.DefaultResponseDTO;
import br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.response.include.CreditStatusResponse;
import br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.response.pooling.PoolingResponseDTO;
import br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.response.viewdata.ViewDataResponseDTO;
import br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.response.viewdocuments.ViewDocumentsResponseDTO;
import br.com.douglastuiuiu.biometricengine.integration.creddefense.integration.CreddefenseIntegration;
import br.com.douglastuiuiu.biometricengine.integration.minio.integration.MinioIntegration;
import br.com.douglastuiuiu.biometricengine.model.builder.AnalyzeResponseDTOBuilder;
import br.com.douglastuiuiu.biometricengine.model.document.Analyze;
import br.com.douglastuiuiu.biometricengine.model.document.AnalyzeRequest;
import br.com.douglastuiuiu.biometricengine.model.dto.*;
import br.com.douglastuiuiu.biometricengine.model.enumeration.AnalyzeStatusEnum;
import br.com.douglastuiuiu.biometricengine.model.enumeration.DocumentTypeEnum;
import br.com.douglastuiuiu.biometricengine.model.enumeration.StorageStatusEnum;
import br.com.douglastuiuiu.biometricengine.model.enumeration.creddefense.CredDefenseAnalyzeStateEnum;
import br.com.douglastuiuiu.biometricengine.model.enumeration.creddefense.PhotoSufixEnum;
import br.com.douglastuiuiu.biometricengine.persistence.AnalyzePersistence;
import br.com.douglastuiuiu.biometricengine.persistence.AnalyzeRequestPersistence;
import br.com.douglastuiuiu.biometricengine.queue.dto.AnalyzeRequestMessage;
import br.com.douglastuiuiu.biometricengine.queue.producer.BiometricProducer;
import br.com.douglastuiuiu.biometricengine.util.MessageLocale;
import br.com.douglastuiuiu.biometricengine.util.Normalizer;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author douglas
 * @since 21/03/2017
 */
@Service
public class CredDefenseService implements IBiometricService {

    private final Logger logger = LoggerFactory.getLogger(CredDefenseService.class);

    @Autowired
    private CreddefenseIntegration creddefenseIntegration;
    @Autowired
    private AnalyzeRequestPersistence analyzeRequestPersistence;
    @Autowired
    private AnalyzePersistence analyzePersistence;
    @Autowired
    private MessageLocale messageLocale;
    @Autowired
    private MinioIntegration minioIntegration;
    @Autowired
    private BiometricProducer biometricProducer;
    @Autowired
    private StorageService storageService;

    @Override
    public ResponseEntity<AnalyzeResponseDTO> createAnalyzeRequest(AnalyzeRequestDTO analyzeRequestDTO) throws ServiceException {
        try {
            analyzeRequestDTO.getPerson().setCpf(Normalizer.normalizeText(analyzeRequestDTO.getPerson().getCpf()));

            AnalyzeRequest analyzeRequest = validadeAlreadyExists(analyzeRequestDTO);
            if (analyzeRequest != null) {
                AnalyzeResponseDTO analyzeResponseDTO = AnalyzeResponseDTOBuilder.newInstance()
                        .withId(analyzeRequest.getId())
                        .withStatus(analyzeRequest.getStatus())
                        .withDescription(HttpStatus.ALREADY_REPORTED.name())
                        .withBureauId(analyzeRequest.getBureauId())
                        .build();
                return new ResponseEntity<>(analyzeResponseDTO, HttpStatus.ALREADY_REPORTED);
            }

            if (!hasPhoto(analyzeRequestDTO)) {
                analyzeRequestDTO.getPerson().setPhoto(null);
                return new ResponseEntity<>(sendAnalyze(analyzeRequestDTO), HttpStatus.CREATED);
            } else {
                storageService.validateImagemSize(analyzeRequestDTO.getPerson().getPhoto().getBase64());
                storageService.validateImagemMimeType(analyzeRequestDTO.getPerson().getPhoto().getBase64());

                DefaultResponseDTO defaultResponseDTO = creddefenseIntegration.assessment(analyzeRequestDTO.getPerson().getPhoto().getBase64());
                if (isValidAssessment(defaultResponseDTO)) {
                    return new ResponseEntity<>(sendAnalyze(analyzeRequestDTO), HttpStatus.CREATED);
                }

                analyzeRequestDTO.setStatus(AnalyzeStatusEnum.PENDENT);
                analyzeRequestDTO.getPerson().setPhoto(null);
                AnalyzeRequest savedAnalyzeRequest = saveAnalyzeRequest(analyzeRequestDTO);

                if (hasDocuments(analyzeRequestDTO)) {
                    List<DocumentDTO> documents = analyzeRequestDTO.getPerson().getDocuments();

                    for (DocumentDTO documentDTO : documents) {
                        storageService.validateImagemSize(documentDTO.getBase64());
                        storageService.validateImagemMimeType(documentDTO.getBase64());
                        documentDTO.setName(savedAnalyzeRequest.getId().concat("_").concat(documentDTO.getType().name()));
                    }

                    minioIntegration.removeFiles(savedAnalyzeRequest.getId());
                    minioIntegration.uploadDocuments(documents);
                }

                AnalyzeResponseDTO analyzeResponseDTO = AnalyzeResponseDTOBuilder.newInstance()
                        .withId(savedAnalyzeRequest.getId())
                        .withStatus(analyzeRequestDTO.getStatus())
                        .withDescription(defaultResponseDTO.getAssessment().get(0).getMessages())
                        .build();
                return new ResponseEntity<>(analyzeResponseDTO, HttpStatus.CREATED);
            }
        } catch (AlreadyReportedException e) {
            throw new AlreadyReportedException(e.getMessage(), e);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public AnalyzeResponseDTO updatePhoto(String id, PhotoDTO photoDTO) throws ServiceException {
        try {
            AnalyzeRequest analyzeRequest = analyzeRequestPersistence.findById(id);
            if (analyzeRequest == null) {
                throw new NotFoundException(messageLocale.getMessage("error.analyze_not_found"));
            }
            if (analyzeRequest.getStatus().compareTo(AnalyzeStatusEnum.PENDENT) != 0) {

                throw new ConflictException(messageLocale.getMessage("error.analyze_in_progress"));
            }
            AnalyzeRequestDTO analyzeRequestDTO = AnalyzeRequestDTO.parseAnalyzeRequestDTO(analyzeRequest);
            analyzeRequestDTO.setId(analyzeRequest.getId());
            analyzeRequestDTO.getPerson().setPhoto(photoDTO);

            //quando trata-se de um update de foto precisa-se pegar os documentos
            //salvos no minio para enviar para a credefense
            for (DocumentDTO doc : analyzeRequestDTO.getPerson().getDocuments()) {
                doc.setBase64(minioIntegration.getFile(analyzeRequestDTO.getId().concat("_").concat(doc.getType().name())));
            }

            if (!hasPhoto(analyzeRequestDTO)) {
                return sendAnalyze(analyzeRequestDTO);
            } else {
                storageService.validateImagemSize(analyzeRequestDTO.getPerson().getPhoto().getBase64());
                storageService.validateImagemMimeType(analyzeRequestDTO.getPerson().getPhoto().getBase64());

                DefaultResponseDTO defaultResponseDTO = creddefenseIntegration.assessment(analyzeRequestDTO.getPerson().getPhoto().getBase64());
                if (isValidAssessment(defaultResponseDTO)) {
                    return sendAnalyze(analyzeRequestDTO);
                }

                analyzeRequestDTO.setStatus(AnalyzeStatusEnum.PENDENT);
                AnalyzeRequest savedAnalyzeRequest = saveAnalyzeRequest(analyzeRequestDTO);

                AnalyzeResponseDTO analyzeResponseDTO = AnalyzeResponseDTOBuilder.newInstance()
                        .withId(savedAnalyzeRequest.getId())
                        .withStatus(analyzeRequestDTO.getStatus())
                        .withDescription(defaultResponseDTO.getAssessment().get(0).getMessages())
                        .build();
                return analyzeResponseDTO;
            }
        } catch (NotFoundException e) {
            throw new NotFoundException(e.getMessage(), e);
        } catch (ConflictException e) {
            throw new ConflictException(e.getMessage(), e);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public ResponseEntity<Object> getAnalyze(String idAnalyzeRequest) throws ServiceException {
        try {
            AnalyzeRequest analyzeRequest = analyzeRequestPersistence.findById(idAnalyzeRequest);
            if (analyzeRequest.getStatus().compareTo(AnalyzeStatusEnum.PROCCESSED) != 0) {
                AnalyzeResponseDTO analyzeResponseDTO = AnalyzeResponseDTOBuilder.newInstance()
                        .withId(analyzeRequest.getId())
                        .withStatus(analyzeRequest.getStatus())
                        .withBureauId(analyzeRequest.getBureauId())
                        .build();
                return new ResponseEntity<>(analyzeResponseDTO, HttpStatus.BAD_REQUEST);
            }
            Analyze analyze = analyzePersistence.findByAnalyzeRequestId(idAnalyzeRequest);
            if (analyzeRequest == null || analyze == null) {
                throw new NotFoundException(messageLocale.getMessage("error.analyze_not_found"));
            }
            if (StorageStatusEnum.PURGED.equals(analyze.getStorageStatus())) {
                // TODO: 4/11/17 buscar imagens da creddefense, salvar no minio e alterar o analyze.getStorageStatus() para StorageStatusEnum.AVAILABLE
            }

            AnalyzeDTO analyzeDTO = AnalyzeDTO.parseAnalyzeDTO(analyze);
            analyzeDTO = setAnalyzeDTOImages(analyze, analyzeDTO);
            return new ResponseEntity<>(analyzeDTO, HttpStatus.OK);
        } catch (NotFoundException e) {
            throw new NotFoundException(e.getMessage(), e);
        } catch (NotProcessedException e) {
            throw new NotProcessedException(e.getMessage(), e);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void setAnalyzeFraud(String idAnalyzeRequest, CommentDTO commentDTO) throws ServiceException {
        try {
            AnalyzeRequest analyzeRequest = analyzeRequestPersistence.findById(idAnalyzeRequest);
            Analyze analyze = analyzePersistence.findByAnalyzeRequestId(idAnalyzeRequest);
            if (analyzeRequest == null || analyze == null) {
                throw new NotFoundException(messageLocale.getMessage("error.analyze_not_found"));
            }
            if (analyzeRequest.getStatus().compareTo(AnalyzeStatusEnum.PROCCESSED) != 0) {
                throw new NotProcessedException(messageLocale.getMessage("error.analyze_unprocessed"));
            }

            creddefenseIntegration.changeStatus(Integer.parseInt(analyzeRequest.getBureauId()), CreditStatusResponse.RESTRITO, commentDTO.getMessage());

            analyzeRequest.setStatus(AnalyzeStatusEnum.DONE);
            analyzeRequestPersistence.updateAndFind(analyzeRequest);

            analyze.setComment(commentDTO.getMessage());
            analyze.setStorageStatus(StorageStatusEnum.PURGED);
            analyzePersistence.updateAndFind(analyze);
        } catch (NotFoundException e) {
            throw new NotFoundException(e.getMessage(), e);
        } catch (NotProcessedException e) {
            throw new NotProcessedException(e.getMessage(), e);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void removeDocuments(String id) throws ServiceException {
        try {
            minioIntegration.removeFiles(id);
        } catch (IntegrationException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public AnalyzeResponseDTO uploadDocuments(String idAnalyzeRequest, List<DocumentDTO> documents) throws ServiceException {

        try {
            AnalyzeRequest analyzeRequest = analyzeRequestPersistence.findById(idAnalyzeRequest);

            if (analyzeRequest == null) {
                throw new NotFoundException(messageLocale.getMessage("error.analyze_not_found"));
            }

            if ((analyzeRequest.getStatus().compareTo(AnalyzeStatusEnum.IN_PROGRESS) != 0) && (analyzeRequest.getStatus().compareTo(AnalyzeStatusEnum.PROCCESSED) != 0)) {
                throw new NotProcessedException(messageLocale.getMessage("error.analyze_unprocessed"));
            }

            List<UploadDocumentsDocumentDTO> uploadDocumentsDocumentList = documents.stream()
                    .map(document -> new UploadDocumentsDocumentDTO(document.getType().getId(), document.getBase64()))
                    .collect(Collectors.toList());

            creddefenseIntegration.uploadDocument(Integer.parseInt(analyzeRequest.getBureauId()), uploadDocumentsDocumentList);

            return AnalyzeResponseDTOBuilder.newInstance()
                    .withId(idAnalyzeRequest)
                    .withStatus(analyzeRequest.getStatus())
                    .withBureauId(analyzeRequest.getBureauId())
                    .build();
        } catch (NotProcessedException e) {
            throw new NotProcessedException(e.getMessage(), e);
        } catch (IntegrationException e) {
            throw new ServiceException(e.getMessage(), e);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e);
        }

    }

    private AnalyzeResponseDTO sendAnalyze(AnalyzeRequestDTO analyzeRequestDTO) throws IntegrationException, JsonProcessingException, ServiceException {
        List<CreditDocumentDTO> creditDocumentDTO = new ArrayList<>();
        for (DocumentDTO documentDTO : analyzeRequestDTO.getPerson().getDocuments()) {
            storageService.validateImagemSize(documentDTO.getBase64());
            storageService.validateImagemMimeType(documentDTO.getBase64());
            creditDocumentDTO.add(new CreditDocumentDTO(documentDTO.getType().getId(), documentDTO.getBase64()));
        }

        DefaultResponseDTO credit = creddefenseIntegration.credit(analyzeRequestDTO.getPerson().getCpf(),
                "C" + analyzeRequestDTO.getEstablishmentId(),
                analyzeRequestDTO.getPerson().getName(),
                analyzeRequestDTO.getPerson().getBirthdate(),
                hasPhoto(analyzeRequestDTO) ? analyzeRequestDTO.getPerson().getPhoto().getBase64() : "",
                creditDocumentDTO);

        if (isSuccessRequest(credit)) {
            analyzeRequestDTO.setStatus(AnalyzeStatusEnum.IN_PROGRESS);
            analyzeRequestDTO.setBureauId(credit.getCreditrequest().get(0).getId().toString());

            AnalyzeRequest savedAnalyzeRequest = saveAnalyzeRequest(analyzeRequestDTO);

            biometricProducer.enqueueMessage(savedAnalyzeRequest.getId(), biometricProducer.getRoutingKeyCredDefense());
            minioIntegration.removeFiles(savedAnalyzeRequest.getId());

            AnalyzeResponseDTO analyzeResponseDTO = AnalyzeResponseDTOBuilder.newInstance()
                    .withId(savedAnalyzeRequest.getId())
                    .withBureauId(savedAnalyzeRequest.getBureauId())
                    .withStatus(analyzeRequestDTO.getStatus())
                    .build();
            return analyzeResponseDTO;
        } else {
            throw new IntegrationException(messageLocale.getMessage("error.analyze_sent_failed"));
        }
    }

    public void checkAnalyzeStatus(AnalyzeRequestMessage analyzeRequestMessage) throws ServiceException {
        AnalyzeRequest analyzeRequest = analyzeRequestPersistence.findById(analyzeRequestMessage.getId());
        try {
            ViewDataResponseDTO viewDataResponseDTO = creddefenseIntegration.viewData(Integer.parseInt(analyzeRequest.getBureauId()));
            if (isProccessedAnalysis(viewDataResponseDTO)) {
                PoolingResponseDTO responseDTO = creddefenseIntegration.pooling(Integer.parseInt(analyzeRequest.getBureauId()));
                ViewDocumentsResponseDTO viewDocumentsDTO = null;
                if (analyzeRequest.getPerson().getDocuments() != null && !analyzeRequest.getPerson().getDocuments().isEmpty()) {
                    viewDocumentsDTO = creddefenseIntegration.viewDocuments(Integer.parseInt(analyzeRequest.getBureauId()));
                }
                responseDTO.setId(analyzeRequestMessage.getId());

                createAnalyze(responseDTO, viewDocumentsDTO, analyzeRequest.getBureauId());

                analyzeRequest.setStatus(AnalyzeStatusEnum.PROCCESSED);
                analyzeRequestPersistence.updateAndFind(analyzeRequest);
            } else {
                biometricProducer.requeueMessage(analyzeRequest.getId(), biometricProducer.getRoutingKeyCredDefense());
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            biometricProducer.requeueMessageByError(analyzeRequest.getId(), biometricProducer.getRoutingKeyCredDefense());
        }
    }

    private void createAnalyze(PoolingResponseDTO poolingResponseDTO, ViewDocumentsResponseDTO viewDocumentsDTO, String bureauId) throws ServiceException {
        try {
            HashMap<String, String> photos = new HashMap<>();
            String id = poolingResponseDTO.getId();
            minioIntegration.removeFiles(id);

            if (viewDocumentsDTO != null && viewDocumentsDTO.getCreditrequest().getDocuments() != null) {
                viewDocumentsDTO.getCreditrequest().getDocuments().stream().filter(Objects::nonNull).forEach(creditResponseDTO -> {
                    String key = id.concat("_").concat(DocumentTypeEnum.getByCode(creditResponseDTO.getType()).name());
                    String photo = creditResponseDTO.getImage();

                    if (photo != null) {
                        photos.put(key, photo);
                    }
                });
            }
            if (poolingResponseDTO.getCreditrequest().getCol1().getCreditrequest() != null) {
                String key = id.concat(PhotoSufixEnum.CREDITREQUEST.getName());
                String photo = poolingResponseDTO.getCreditrequest().getCol1().getCreditrequest().getPhoto();

                if (photo != null) {
                    photos.put(key, photo);
                }
            }
            if (poolingResponseDTO.getCreditrequest().getCol2() != null) {
                if (poolingResponseDTO.getCreditrequest().getCol2().getCustomer() != null) {
                    String key = id.concat(PhotoSufixEnum.CUSTOMER.getName());
                    String photo = poolingResponseDTO.getCreditrequest().getCol2().getCustomer().getPhoto();

                    if (photo != null) {
                        photos.put(key, photo);
                    }
                }
                if (poolingResponseDTO.getCreditrequest().getCol2().getFraud() != null) {
                    for (Integer i = 0; i < poolingResponseDTO.getCreditrequest().getCol2().getFraud().size(); i++) {
                        String key = id.concat(PhotoSufixEnum.FRAUD.getName()).concat(i.toString());
                        String photo = poolingResponseDTO.getCreditrequest().getCol2().getFraud().get(i).getPhoto();

                        if (photo != null) {
                            photos.put(key, photo);
                        }
                    }
                }
            }
            if (poolingResponseDTO.getCreditrequest().getCol3() != null) {
                if (poolingResponseDTO.getCreditrequest().getCol3().getCustomer_similars() != null) {
                    for (Integer i = 0; i < poolingResponseDTO.getCreditrequest().getCol3().getCustomer_similars().size(); i++) {
                        String key = id.concat(PhotoSufixEnum.CUSTOMER_SIMILAR.getName()).concat(i.toString());
                        String photo = poolingResponseDTO.getCreditrequest().getCol3().getCustomer_similars().get(i).getPhoto();

                        if (photo != null) {
                            photos.put(key, photo);
                        }
                    }
                }
                if (poolingResponseDTO.getCreditrequest().getCol3().getCreditrequest_similars() != null) {
                    for (Integer i = 0; i < poolingResponseDTO.getCreditrequest().getCol3().getCreditrequest_similars().size(); i++) {
                        String key = id.concat(PhotoSufixEnum.CREDIREQUEST_SIMILAR.getName()).concat(i.toString());
                        String photo = poolingResponseDTO.getCreditrequest().getCol3().getCreditrequest_similars().get(i).getPhoto();

                        if (photo != null) {
                            photos.put(key, photo);
                        }
                    }
                }
            }
            for (String key : photos.keySet()) {
                minioIntegration.uploadFile(key, photos.get(key));
            }

            saveAnalyze(poolingResponseDTO, viewDocumentsDTO, bureauId);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    private AnalyzeDTO setAnalyzeDTOImages(Analyze analyze, AnalyzeDTO analyzeDTO) throws Exception {
        //request
        if (analyze.getRequest() != null) {
            if (analyzeDTO.getRequest().getPerson().getPhoto() != null) {
                analyzeDTO.getRequest().getPerson().getPhoto().setBase64(minioIntegration.getFile(analyze.getRequest().getPerson().getPhoto().getName()));
            }
            if (analyzeDTO.getRequest().getPerson().getDocuments() != null) {
                for (int i = 0; i < analyzeDTO.getRequest().getPerson().getDocuments().size(); i++) {
                    analyzeDTO.getRequest().getPerson().getDocuments().get(i).setBase64(minioIntegration.getFile(analyze.getRequest().getId().concat("_").concat(analyze.getRequest().getPerson().getDocuments().get(i).getType().name())));
                }
            }
        }

        //customer
        if (analyze.getCustomer() != null) {
            if (analyze.getCustomer().getPhoto() != null) {
                analyzeDTO.getCustomer().getPhoto().setBase64(minioIntegration.getFile(analyze.getCustomer().getPhoto().getName()));
            }
        }

        //customer similars
        if (analyze.getCustomerSimilars() != null) {
            for (int i = 0; i < analyze.getCustomerSimilars().size(); i++) {
                if (analyze.getCustomerSimilars().get(i) != null) {
                    if (analyze.getCustomerSimilars().get(i).getPhoto() != null) {
                        analyzeDTO.getCustomerSimilars().get(i).getPhoto().setBase64(minioIntegration.getFile(analyze.getCustomerSimilars().get(i).getPhoto().getName()));
                    }
                }
            }
        }

        //request similars
        if (analyze.getRequestSimilars() != null) {
            for (int i = 0; i < analyze.getRequestSimilars().size(); i++) {
                if (analyze.getRequestSimilars().get(i) != null) {
                    if (analyze.getRequestSimilars().get(i).getPhoto() != null) {
                        analyzeDTO.getRequestSimilars().get(i).getPhoto().setBase64(minioIntegration.getFile(analyze.getRequestSimilars().get(i).getPhoto().getName()));
                    }
                }
            }
        }

        return analyzeDTO;
    }

    private boolean isProccessedAnalysis(ViewDataResponseDTO viewDataResponseDTO) {
        return viewDataResponseDTO != null
                && viewDataResponseDTO.getCreditrequest() != null
                && viewDataResponseDTO.getCreditrequest().getState() != null
                && CredDefenseAnalyzeStateEnum.AWAITING_ANALYSIS.getName().compareTo(viewDataResponseDTO.getCreditrequest().getState()) == 0;
    }

    private AnalyzeRequest validadeAlreadyExists(AnalyzeRequestDTO analyzeRequestDTO) throws AlreadyReportedException {
        List<AnalyzeStatusEnum> statusList = new ArrayList<>();
        statusList.add(AnalyzeStatusEnum.PENDENT);
        statusList.add(AnalyzeStatusEnum.IN_PROGRESS);
        return analyzeRequestPersistence.findByStatusAndConsumerTypeAndCpfInOrderByIdDesc(statusList, analyzeRequestDTO.getConsumerType(), analyzeRequestDTO.getPerson().getCpf());
    }

    private AnalyzeRequest saveAnalyzeRequest(AnalyzeRequestDTO analyzeRequestDTO) {
        AnalyzeRequest analyzeRequest = AnalyzeRequest.parseAnalyzeRequest(analyzeRequestDTO);

        if (analyzeRequest.getId() != null) {
            return analyzeRequestPersistence.updateAndFind(analyzeRequest);
        } else {
            return analyzeRequestPersistence.insertAndFind(analyzeRequest);
        }
    }

    private void saveAnalyze(PoolingResponseDTO poolingResponseDTO, ViewDocumentsResponseDTO viewDocumentsDTO, String bureauId) throws Exception {
        Analyze analyze = Analyze.parseAnalyze(poolingResponseDTO, viewDocumentsDTO, bureauId);
        analyze.setStorageStatus(StorageStatusEnum.AVAILABLE);

        Analyze analyzeBD = analyzePersistence.findByAnalyzeRequestId(analyze.getRequest().getId());
        if (analyzeBD != null) {
            analyzePersistence.updateAndFind(analyze);
        } else {
            analyzePersistence.insert(analyze);
        }
    }

    private boolean hasDocuments(AnalyzeRequestDTO analyzeRequestDTO) {
        return analyzeRequestDTO.getPerson() != null && analyzeRequestDTO.getPerson().getDocuments() != null && analyzeRequestDTO.getPerson().getDocuments().size() > 0;
    }

    private boolean isSuccessRequest(DefaultResponseDTO credit) {
        return credit.getCreditrequest() != null && credit.getCreditrequest().size() > 0;
    }

    private boolean isValidAssessment(DefaultResponseDTO defaultResponseDTO) {
        return defaultResponseDTO != null && defaultResponseDTO.getAssessment() != null && defaultResponseDTO.getAssessment().size() > 0 && "success".equalsIgnoreCase(defaultResponseDTO.getAssessment().get(0).getResult());
    }

    private boolean hasPhoto(AnalyzeRequestDTO analyzeRequestDTO) {
        return analyzeRequestDTO.getPerson().getPhoto() != null && analyzeRequestDTO.getPerson().getPhoto().getBase64() != null && !analyzeRequestDTO.getPerson().getPhoto().getBase64().isEmpty();
    }

}
