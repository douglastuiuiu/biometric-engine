package br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.response.include;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @author douglas
 * @since 20/03/2017
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreditResponseDTO implements Serializable {

    private static final long serialVersionUID = -3138036651449510885L;

    private Integer id;
    private String state;
    @JsonProperty("state_id")
    private String stateId;
    @JsonProperty("identifier_code")
    private String identifierCode;
    @JsonProperty("document_type")
    private String documentType = "2";
    @JsonProperty("place_id")
    private String placeId;
    private String name;
    private String fraud;
    @JsonProperty("birth_date")
    private String birthDate;
    private String photo;
    private List<DocumentResponseDTO> document;
    private List<DocumentResponseDTO> documents;
    private String identification;
    private String similarity;
    @JsonProperty("insert_date")
    private String insertDate;
    @JsonProperty("insert_time")
    private String insertTime;
    private String place;
    private String status;
    private String pending;
    private String comment;
    private String result;
    private String messages;
    private ContentCol1 col1;
    private ContentCol2 col2;
    private ContentCol3 col3;

    public String getIdentifierCode() {
        return identifierCode;
    }

    public void setIdentifierCode(String identifierCode) {
        this.identifierCode = identifierCode;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public List<DocumentResponseDTO> getDocument() {
        return document;
    }

    public void setDocument(List<DocumentResponseDTO> document) {
        this.document = document;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStateId() {
        return stateId;
    }

    public void setStateId(String stateId) {
        this.stateId = stateId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getSimilarity() {
        return similarity;
    }

    public void setSimilarity(String similarity) {
        this.similarity = similarity;
    }

    public String getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(String insertDate) {
        this.insertDate = insertDate;
    }

    public String getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(String insertTime) {
        this.insertTime = insertTime;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPending() {
        return pending;
    }

    public void setPending(String pending) {
        this.pending = pending;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

    public List<DocumentResponseDTO> getDocuments() {
        return documents;
    }

    public void setDocuments(List<DocumentResponseDTO> documents) {
        this.documents = documents;
    }

    public ContentCol1 getCol1() {
        return col1;
    }

    public void setCol1(ContentCol1 col1) {
        this.col1 = col1;
    }

    public ContentCol2 getCol2() {
        return col2;
    }

    public void setCol2(ContentCol2 col2) {
        this.col2 = col2;
    }

    public ContentCol3 getCol3() {
        return col3;
    }

    public void setCol3(ContentCol3 col3) {
        this.col3 = col3;
    }

    public String getFraud() {
        return fraud;
    }

    public void setFraud(String fraud) {
        this.fraud = fraud;
    }

    public class ContentCol1 {

        private CreditResponseDTO creditrequest;

        public ContentCol1() {
        }

        public CreditResponseDTO getCreditrequest() {
            return creditrequest;
        }

        public void setCreditrequest(CreditResponseDTO creditrequest) {
            this.creditrequest = creditrequest;
        }

    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public class ContentCol2 {

        private CreditResponseDTO customer;

        private List<CreditResponseDTO> fraud;

        public ContentCol2() {
        }

        public CreditResponseDTO getCustomer() {
            return customer;
        }

        public void setCustomer(CreditResponseDTO customer) {
            this.customer = customer;
        }

        public List<CreditResponseDTO> getFraud() {
            return fraud;
        }

        public void setFraud(List<CreditResponseDTO> fraud) {
            this.fraud = fraud;
        }

    }

    public class ContentCol3 {

        private List<CreditResponseDTO> creditrequest_similars;

        private List<CreditResponseDTO> customer_similars;

        public ContentCol3() {
        }

        public List<CreditResponseDTO> getCreditrequest_similars() {
            return creditrequest_similars;
        }

        public void setCreditrequest_similars(List<CreditResponseDTO> creditrequest_similars) {
            this.creditrequest_similars = creditrequest_similars;
        }

        public List<CreditResponseDTO> getCustomer_similars() {
            return customer_similars;
        }

        public void setCustomer_similars(List<CreditResponseDTO> customer_similars) {
            this.customer_similars = customer_similars;
        }

    }
}
