package br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.response.viewdata;

import java.io.Serializable;

/**
 * @author douglas
 * @since 20/03/2017
 */
public class ViewDataCol2FraudDTO implements Serializable {

    private static final long serialVersionUID = -3454085782233631711L;

    private Integer id;
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
