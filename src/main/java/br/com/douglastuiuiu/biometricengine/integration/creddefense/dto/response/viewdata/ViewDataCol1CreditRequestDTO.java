package br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.response.viewdata;

import java.io.Serializable;

/**
 * @author douglas
 * @since 31/03/2017
 */
public class ViewDataCol1CreditRequestDTO implements Serializable {

    private static final long serialVersionUID = 811623354341692071L;

    private String identification;
    private String similarity;
    private String name;
    private String identifier_code;
    private String birth_date;
    private String insert_date;
    private Integer place_id;
    private String place;
    private String status;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentifier_code() {
        return identifier_code;
    }

    public void setIdentifier_code(String identifier_code) {
        this.identifier_code = identifier_code;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public String getInsert_date() {
        return insert_date;
    }

    public void setInsert_date(String insert_date) {
        this.insert_date = insert_date;
    }

    public Integer getPlace_id() {
        return place_id;
    }

    public void setPlace_id(Integer place_id) {
        this.place_id = place_id;
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
}
