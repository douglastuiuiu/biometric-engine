package br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.request.entity.credit;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @author douglas
 * @since 20/03/2017
 */
public class CreditDTO implements Serializable {

    private static final long serialVersionUID = -9146702859542134482L;

    @JsonProperty("identifier_code")
    private String identifierCode;
    @JsonProperty("document_type")
    private String documentType = "2";
    @JsonProperty("place_id")
    private String placeId;
    private String name;
    @JsonProperty("birth_date")
    private String birthDate;
    private String photo;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<CreditDocumentDTO> document;

    public CreditDTO() {
    }

    public CreditDTO(String identifierCode, String placeId, String name, String birthDate, String photo, List<CreditDocumentDTO> document) {
        super();
        this.identifierCode = identifierCode;
        this.placeId = placeId;
        this.name = name;
        this.birthDate = birthDate;
        this.photo = photo;
        this.document = document;
    }

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

    public List<CreditDocumentDTO> getDocument() {
        return document;
    }

    public void setDocument(List<CreditDocumentDTO> document) {
        this.document = document;
    }
}
