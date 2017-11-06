package br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.response.pooling;

import java.io.Serializable;

/**
 * @author douglas
 * @since 20/03/2017
 */
public class PoolingCol2FraudDTO implements Serializable {

    private static final long serialVersionUID = 5953440733674354475L;

    private Integer id;
    private String photo;
    private String name;
    private String identifier_code;
    private String birth_date;
    private String insert_date;
    private String insert_time;
    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
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

    public String getInsert_time() {
        return insert_time;
    }

    public void setInsert_time(String insert_time) {
        this.insert_time = insert_time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
