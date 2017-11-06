package br.com.douglastuiuiu.biometricengine.model.document;

import br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.response.pooling.*;
import br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.response.viewdocuments.ViewDocumentsResponseDTO;
import br.com.douglastuiuiu.biometricengine.model.builder.AnalyzeBuilder;
import br.com.douglastuiuiu.biometricengine.model.builder.AnalyzeRequestBuilder;
import br.com.douglastuiuiu.biometricengine.model.builder.PersonBuilder;
import br.com.douglastuiuiu.biometricengine.model.enumeration.DocumentTypeEnum;
import br.com.douglastuiuiu.biometricengine.model.enumeration.StorageStatusEnum;
import br.com.douglastuiuiu.biometricengine.model.enumeration.creddefense.PhotoSufixEnum;
import br.com.douglastuiuiu.biometricengine.serializer.LocalDateTimeDeserializer;
import br.com.douglastuiuiu.biometricengine.serializer.LocalDateTimeSerializer;
import br.com.douglastuiuiu.biometricengine.util.DateUtil;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * @author douglas
 * @since 23/03/2017
 */
@org.springframework.data.mongodb.core.mapping.Document(collection = "analyze")
@CompoundIndexes({@CompoundIndex(name = "index_analyze", def = "{'analyzeRequest.consumerType' : 1, 'analyzeRequest.status' : 1, 'analyzeRequest.person.cpf': 1}")})
public class Analyze implements Serializable {

    private static final long serialVersionUID = -1701136906285938585L;

    @Id
    private String id;
    @Field
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime registerDate;
    @Field
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime lastUpdate;
    @Field
    private String bureauStatus;
    @Field
    private AnalyzeRequest request;
    @Field
    private Person customer;
    @Field
    private List<Person> fraud;
    @Field
    private List<Person> customerSimilars;
    @Field
    private List<Person> requestSimilars;
    @Field
    private StorageStatusEnum storageStatus;
    @Field
    private String comment;

    public Analyze() {

    }

    public static Analyze parseAnalyze(PoolingResponseDTO poolingResponseDTO, ViewDocumentsResponseDTO viewDocumentsDTO, String bureauId) throws Exception {
        PoolingCreditDTO creditrequest = poolingResponseDTO.getCreditrequest();
        String idRequest = poolingResponseDTO.getId();

        AnalyzeRequest request = null;
        HashMap<DocumentTypeEnum, Document> documents = new HashMap<>();
        if (viewDocumentsDTO != null) {
            viewDocumentsDTO.getCreditrequest().getDocuments().stream().filter(Objects::nonNull).forEach(dto -> {
                Document document = new Document(DocumentTypeEnum.getByCode(dto.getType()));
                documents.put(document.getType(), document);
            });
        }
        if (creditrequest.getCol1() != null && creditrequest.getCol1().getCreditrequest() != null) {
            request = AnalyzeRequestBuilder.newInstance()
                    .withId(poolingResponseDTO.getId())
                    .withEstablishmentId(creditrequest.getCol1().getCreditrequest().getPlace_id().toString())
                    .withPerson(PersonBuilder.newInstance()
                            .withCpf(creditrequest.getCol1().getCreditrequest().getIdentifier_code())
                            .withName(creditrequest.getCol1().getCreditrequest().getName())
                            .withBirthdate(creditrequest.getCol1().getCreditrequest().getBirth_date() != null ? DateUtil.stringToLocalDate(creditrequest.getCol1().getCreditrequest().getBirth_date(), DateUtil.DD_MM_YYYY) : null)
                            .withBureauRegisterDate(DateUtil.stringToLocalDate(creditrequest.getCol1().getCreditrequest().getInsert_date(), DateUtil.DD_MM_YYYY))
                            .withPhoto(idRequest, creditrequest.getCol1().getCreditrequest().getPhoto(), PhotoSufixEnum.CREDITREQUEST.getName())
                            .withDocuments(new ArrayList<>(documents.values()))
                            .build())
                    .build();
        }

        Person customer = null;
        List<Person> fraud = null;
        if (creditrequest.getCol2() != null) {
            if (creditrequest.getCol2().getCustomer() != null) {
                customer = PersonBuilder.newInstance()
                        .withCpf(creditrequest.getCol2().getCustomer().getIdentifier_code())
                        .withName(creditrequest.getCol2().getCustomer().getName())
                        .withBirthdate(creditrequest.getCol2().getCustomer().getBirth_date() != null ? DateUtil.stringToLocalDate(creditrequest.getCol2().getCustomer().getBirth_date(), DateUtil.DD_MM_YYYY) : null)
                        .withBureauRegisterDate(DateUtil.stringToLocalDate(creditrequest.getCol2().getCustomer().getInsert_date(), DateUtil.DD_MM_YYYY))
                        .withPhoto(idRequest, creditrequest.getCol2().getCustomer().getPhoto(), PhotoSufixEnum.CUSTOMER.getName())
                        .build();
            }

            if (creditrequest.getCol2().getFraud() != null) {
                fraud = new ArrayList<>();
                for (Integer i = 0; i < creditrequest.getCol2().getFraud().size(); i++) {
                    PoolingCol2FraudDTO fraudDTO = creditrequest.getCol2().getFraud().get(i);
                    fraud.add(PersonBuilder.newInstance()
                            .withCpf(fraudDTO.getIdentifier_code())
                            .withName(fraudDTO.getName())
                            .withBureauRegisterDate((fraudDTO.getInsert_date() != null ? DateUtil.stringToLocalDate(fraudDTO.getInsert_date(), DateUtil.DD_MM_YYYY) : null))
                            .withPhoto(idRequest, fraudDTO.getPhoto(), PhotoSufixEnum.FRAUD.getName().concat(i.toString()))
                            .build());
                }
            }
        }

        List<Person> customerSimilars = null;
        List<Person> requestSimilars = null;
        if (creditrequest.getCol3() != null) {
            if (creditrequest.getCol3().getCustomer_similars() != null) {
                customerSimilars = new ArrayList<>();
                for (Integer i = 0; i < creditrequest.getCol3().getCustomer_similars().size(); i++) {
                    PoolingCol3CustomerSimilarsDTO customerSimilar = creditrequest.getCol3().getCustomer_similars().get(i);
                    customerSimilars.add(PersonBuilder.newInstance()
                            .withCpf(customerSimilar.getIdentifier_code())
                            .withName(customerSimilar.getName())
                            .withBureauRegisterDate(DateUtil.stringToLocalDate(customerSimilar.getInsert_date(), DateUtil.DD_MM_YYYY))
                            .withPhoto(idRequest, customerSimilar.getPhoto(), PhotoSufixEnum.CUSTOMER_SIMILAR.getName().concat(i.toString()))
                            .build());
                }
            }

            if (creditrequest.getCol3().getCreditrequest_similars() != null) {
                requestSimilars = new ArrayList<>();
                for (Integer i = 0; i < creditrequest.getCol3().getCreditrequest_similars().size(); i++) {
                    PoolingCol3CreditRequestSimilarsDTO requestSimilar = creditrequest.getCol3().getCreditrequest_similars().get(i);
                    requestSimilars.add(PersonBuilder.newInstance()
                            .withCpf(requestSimilar.getIdentifier_code())
                            .withName(requestSimilar.getName())
                            .withBureauRegisterDate(DateUtil.stringToLocalDate(requestSimilar.getInsert_date(), DateUtil.DD_MM_YYYY))
                            .withPhoto(idRequest, requestSimilar.getPhoto(), PhotoSufixEnum.CREDIREQUEST_SIMILAR.getName().concat(i.toString()))
                            .build());
                }
            }
        }

        request.setBureauId(bureauId);

        Analyze analyze = AnalyzeBuilder.newInstance()
                .withRequest(request)
                .withCustomer(customer)
                .withFraud(fraud)
                .withCustomerSimilars(customerSimilars)
                .withRequestSimilars(requestSimilars)
                .withBureauStatus(creditrequest.getCol1().getCreditrequest().getIdentification())
                .build();

        return analyze;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(LocalDateTime registerDate) {
        this.registerDate = registerDate;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getBureauStatus() {
        return bureauStatus;
    }

    public void setBureauStatus(String bureauStatus) {
        this.bureauStatus = bureauStatus;
    }

    public AnalyzeRequest getRequest() {
        return request;
    }

    public void setRequest(AnalyzeRequest request) {
        this.request = request;
    }

    public Person getCustomer() {
        return customer;
    }

    public void setCustomer(Person customer) {
        this.customer = customer;
    }

    public List<Person> getCustomerSimilars() {
        return customerSimilars;
    }

    public void setCustomerSimilars(List<Person> customerSimilars) {
        this.customerSimilars = customerSimilars;
    }

    public List<Person> getFraud() {
        return fraud;
    }

    public void setFraud(List<Person> fraud) {
        this.fraud = fraud;
    }

    public List<Person> getRequestSimilars() {
        return requestSimilars;
    }

    public void setRequestSimilars(List<Person> requestSimilars) {
        this.requestSimilars = requestSimilars;
    }

    public StorageStatusEnum getStorageStatus() {
        return storageStatus;
    }

    public void setStorageStatus(StorageStatusEnum storageStatus) {
        this.storageStatus = storageStatus;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
