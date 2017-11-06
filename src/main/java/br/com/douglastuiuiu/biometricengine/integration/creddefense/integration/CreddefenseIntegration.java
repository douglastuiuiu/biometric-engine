package br.com.douglastuiuiu.biometricengine.integration.creddefense.integration;

import br.com.douglastuiuiu.biometricengine.exception.IntegrationException;
import br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.request.entity.assessment.AssessmentAuthenticationDTO;
import br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.request.entity.assessment.AssessmentDTO;
import br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.request.entity.assessment.AssessmentRequestDTO;
import br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.request.entity.changestatus.ChangeStatusAuthenticationDTO;
import br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.request.entity.changestatus.ChangeStatusCreditDTO;
import br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.request.entity.changestatus.ChangeStatusRequestDTO;
import br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.request.entity.credit.CreditAuthenticationDTO;
import br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.request.entity.credit.CreditDTO;
import br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.request.entity.credit.CreditDocumentDTO;
import br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.request.entity.credit.CreditRequestDTO;
import br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.request.entity.login.LoginAuthenticationDTO;
import br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.request.entity.login.LoginRequestDTO;
import br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.request.entity.logout.LogoutAuthenticationDTO;
import br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.request.entity.logout.LogoutRequestDTO;
import br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.request.entity.pooling.PoolingAuthenticationDTO;
import br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.request.entity.pooling.PoolingCreditDTO;
import br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.request.entity.pooling.PoolingRequestDTO;
import br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.request.entity.status.StatusAuthenticationDTO;
import br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.request.entity.status.StatusCreditDTO;
import br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.request.entity.status.StatusRequestDTO;
import br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.request.entity.uploaddocument.UploadDocumentsAuthenticationDTO;
import br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.request.entity.uploaddocument.UploadDocumentsCreditDTO;
import br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.request.entity.uploaddocument.UploadDocumentsDocumentDTO;
import br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.request.entity.uploaddocument.UploadDocumentsRequestDTO;
import br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.request.entity.viewdata.ViewDataAuthenticationDTO;
import br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.request.entity.viewdata.ViewDataCreditDTO;
import br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.request.entity.viewdata.ViewDataRequestDTO;
import br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.request.entity.viewdocument.ViewDocumentsAuthenticationDTO;
import br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.request.entity.viewdocument.ViewDocumentsCreditDTO;
import br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.request.entity.viewdocument.ViewDocumentsRequestDTO;
import br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.response.DefaultResponseDTO;
import br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.response.changestatus.ChangeStatusResponseDTO;
import br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.response.include.CreditStatusResponse;
import br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.response.pooling.PoolingResponseDTO;
import br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.response.status.StatusResponseDTO;
import br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.response.viewdata.ViewDataResponseDTO;
import br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.response.viewdocuments.ViewDocumentsResponseDTO;
import br.com.douglastuiuiu.biometricengine.integration.creddefense.properties.ApiCreddefensePath;
import br.com.douglastuiuiu.biometricengine.util.DateUtil;
import br.com.douglastuiuiu.biometricengine.util.Normalizer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author douglasg
 * @since 10/03/2017
 */
@Component
public class CreddefenseIntegration {

    private static final String URL_LOGIN = "login/";
    private static final String URL_CREDIT_REQUEST = "creditRequest/";
    private static final String URL_POOLING = "pooling/";
    private static final String URL_LOGOUT = "logout/";
    private static final String URL_UPLOAD_DOCUMENTS = "uploaddocuments/";
    private static final String URL_ASSESSMENT = "assessment/";
    private static final String URL_CHANGE_STATUS = "changestatus/";
    private static final String URL_STATUS = "status/";
    private static final String URL_VIEW_DOCUMENTS = "viewdocuments/";
    private static final String URL_VIEWDATA = "viewdata/";

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper conversorJson = new ObjectMapper();

    @Autowired
    private ApiCreddefensePath apiCreddefensePath;

    public DefaultResponseDTO assessment(String fotoBase64) throws IntegrationException {
        DefaultResponseDTO token = this.login();
        try {
            List<AssessmentDTO> assessmentList = new ArrayList<>();
            assessmentList.add(new AssessmentDTO(fotoBase64));
            AssessmentRequestDTO request = new AssessmentRequestDTO(assessmentList, new AssessmentAuthenticationDTO(token.getAuthentication().getToken()));

            return (DefaultResponseDTO) defaultPost(request, URL_ASSESSMENT).responseValidate();
        } catch (Exception e) {
            throw new IntegrationException(e.getMessage(), e);
        } finally {
            if (token != null && token.getAuthentication() != null && token.getAuthentication().getToken() != null) {
                this.logout(token.getAuthentication().getToken());
            }
        }
    }

    public DefaultResponseDTO credit(String cpf, String idLoja, String nome, LocalDate dataNascimento, String foto, List<CreditDocumentDTO> documentResponseDTO) throws IntegrationException {
        DefaultResponseDTO token = this.login();
        try {
            List<CreditDTO> creditRequestList = new ArrayList<>();
            creditRequestList.add(new CreditDTO(cpf,
                    idLoja,
                    Normalizer.normalizeText(nome),
                    DateUtil.localDateToString(dataNascimento, DateUtil.DD_MM_YYYY),
                    foto != null ? foto : "",
                    documentResponseDTO == null ? null : documentResponseDTO.isEmpty() ? null : documentResponseDTO)
            );
            CreditRequestDTO request = new CreditRequestDTO();
            request.setAuthentication(new CreditAuthenticationDTO(token.getAuthentication().getToken()));
            request.setCreditrequest(creditRequestList);

            return (DefaultResponseDTO) defaultPost(request, URL_CREDIT_REQUEST).responseValidate();
        } catch (Exception e) {
            throw new IntegrationException(e.getMessage(), e);
        } finally {
            if (token != null && token.getAuthentication() != null && token.getAuthentication().getToken() != null) {
                this.logout(token.getAuthentication().getToken());
            }
        }
    }

    public StatusResponseDTO status(Integer creditRequestId) throws IntegrationException {
        DefaultResponseDTO token = this.login();
        try {
            StatusRequestDTO request = new StatusRequestDTO(new StatusCreditDTO(creditRequestId), new StatusAuthenticationDTO(token.getAuthentication().getToken()));

            String bodyJSON = conversorJson.writeValueAsString(request);
            HttpEntity<String> entity = buildHeaders(bodyJSON);
            ResponseEntity<StatusResponseDTO> resposta = restTemplate.postForEntity(apiCreddefensePath.getEndpoint() + URL_STATUS, entity, StatusResponseDTO.class);

            return (StatusResponseDTO) resposta.getBody().responseValidate();
        } catch (Exception e) {
            throw new IntegrationException(e.getMessage(), e);
        } finally {
            if (token != null) {
                this.logout(token.getAuthentication().getToken());
            }
        }
    }

    public ChangeStatusResponseDTO changeStatus(Integer creditRequestId, CreditStatusResponse statusAlterado, String comentario) throws IntegrationException {
        DefaultResponseDTO token = this.login();
        try {
            ChangeStatusRequestDTO request = new ChangeStatusRequestDTO(new ChangeStatusCreditDTO(creditRequestId, statusAlterado.getCodigo(), Normalizer.normalizeText(comentario)), new ChangeStatusAuthenticationDTO(token.getAuthentication().getToken()));

            String bodyJSON = conversorJson.writeValueAsString(request);
            HttpEntity<String> entity = buildHeaders(bodyJSON);
            ResponseEntity<ChangeStatusResponseDTO> resposta = restTemplate.postForEntity(apiCreddefensePath.getEndpoint() + URL_CHANGE_STATUS, entity, ChangeStatusResponseDTO.class);

            return (ChangeStatusResponseDTO) resposta.getBody().responseValidate();
        } catch (Exception e) {
            throw new IntegrationException(e.getMessage(), e);
        } finally {
            if (token != null && token.getAuthentication() != null && token.getAuthentication().getToken() != null) {
                this.logout(token.getAuthentication().getToken());
            }
        }
    }

    public DefaultResponseDTO uploadDocument(Integer creditRequestId, List<UploadDocumentsDocumentDTO> uploadDocumentsDocumentDTO) throws IntegrationException {
        DefaultResponseDTO token = login();
        try {
            UploadDocumentsRequestDTO request = new UploadDocumentsRequestDTO(new UploadDocumentsCreditDTO(creditRequestId, uploadDocumentsDocumentDTO), new UploadDocumentsAuthenticationDTO(token.getAuthentication().getToken()));

            return (DefaultResponseDTO) defaultPost(request, URL_UPLOAD_DOCUMENTS).responseValidate();
        } catch (Exception e) {
            throw new IntegrationException(e.getMessage(), e);
        } finally {
            if (token != null && token.getAuthentication() != null && token.getAuthentication().getToken() != null) {
                this.logout(token.getAuthentication().getToken());
            }
        }
    }

    public ViewDocumentsResponseDTO viewDocuments(Integer creditRequestId) throws IntegrationException {
        DefaultResponseDTO token = login();
        try {
            ViewDocumentsRequestDTO request = new ViewDocumentsRequestDTO(new ViewDocumentsCreditDTO(creditRequestId), new ViewDocumentsAuthenticationDTO(token.getAuthentication().getToken()));

            String bodyJSON = conversorJson.writeValueAsString(request);
            HttpEntity<String> entity = buildHeaders(bodyJSON);
            ResponseEntity<ViewDocumentsResponseDTO> resposta = restTemplate.postForEntity(apiCreddefensePath.getEndpoint() + URL_VIEW_DOCUMENTS, entity, ViewDocumentsResponseDTO.class);

            return (ViewDocumentsResponseDTO) resposta.getBody().responseValidate();
        } catch (Exception e) {
            throw new IntegrationException(e.getMessage(), e);
        } finally {
            if (token != null && token.getAuthentication() != null && token.getAuthentication().getToken() != null) {
                this.logout(token.getAuthentication().getToken());
            }
        }
    }

    public PoolingResponseDTO pooling(Integer creditRequestId) throws IntegrationException {
        DefaultResponseDTO token = this.login();
        try {
            PoolingRequestDTO request = new PoolingRequestDTO(new PoolingCreditDTO(creditRequestId), new PoolingAuthenticationDTO(token.getAuthentication().getToken()));

            String bodyJSON = conversorJson.writeValueAsString(request);
            HttpEntity<String> entity = buildHeaders(bodyJSON);
            ResponseEntity<PoolingResponseDTO> resposta = restTemplate.postForEntity(apiCreddefensePath.getEndpoint() + URL_POOLING, entity, PoolingResponseDTO.class);

            return (PoolingResponseDTO) resposta.getBody().responseValidate();
        } catch (Exception e) {
            throw new IntegrationException(e.getMessage(), e);
        } finally {
            if (token != null && token.getAuthentication() != null && token.getAuthentication().getToken() != null) {
                this.logout(token.getAuthentication().getToken());
            }
        }
    }

    public ViewDataResponseDTO viewData(Integer creditRequestId) throws IntegrationException {
        DefaultResponseDTO token = this.login();
        try {
            ViewDataRequestDTO request = new ViewDataRequestDTO(new ViewDataCreditDTO(creditRequestId), new ViewDataAuthenticationDTO(token.getAuthentication().getToken()));

            String bodyJSON = conversorJson.writeValueAsString(request);
            HttpEntity<String> entity = buildHeaders(bodyJSON);
            ResponseEntity<ViewDataResponseDTO> resposta = restTemplate.postForEntity(apiCreddefensePath.getEndpoint() + URL_VIEWDATA, entity, ViewDataResponseDTO.class);

            return (ViewDataResponseDTO) resposta.getBody().responseValidate();
        } catch (Exception e) {
            throw new IntegrationException(e.getMessage(), e);
        } finally {
            if (token != null && token.getAuthentication() != null && token.getAuthentication().getToken() != null) {
                this.logout(token.getAuthentication().getToken());
            }
        }
    }

    private DefaultResponseDTO login() throws IntegrationException {
        try {
            LoginRequestDTO request = new LoginRequestDTO();
            request.setAuthentication(new LoginAuthenticationDTO(apiCreddefensePath.getUser(), apiCreddefensePath.getPasswd()));

            return (DefaultResponseDTO) defaultPost(request, URL_LOGIN).responseValidate();
        } catch (Exception e) {
            throw new IntegrationException(e.getMessage(), e);
        }
    }

    private DefaultResponseDTO logout(String token) throws IntegrationException {
        try {
            LogoutRequestDTO request = new LogoutRequestDTO();
            request.setAuthentication(new LogoutAuthenticationDTO(token));

            return (DefaultResponseDTO) defaultPost(request, URL_LOGOUT).responseValidate();
        } catch (Exception e) {
            throw new IntegrationException(e.getMessage(), e);
        }
    }

    private DefaultResponseDTO defaultPost(Object request, String path) throws JsonProcessingException {
        String bodyJSON = conversorJson.writeValueAsString(request);
        HttpEntity<String> entity = buildHeaders(bodyJSON);
        ResponseEntity<?> resposta = restTemplate.postForEntity(apiCreddefensePath.getEndpoint() + path, entity, DefaultResponseDTO.class);
        return ((DefaultResponseDTO) resposta.getBody());
    }

    private HttpEntity<String> buildHeaders(String bodyJSON) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(bodyJSON, headers);
    }

}
