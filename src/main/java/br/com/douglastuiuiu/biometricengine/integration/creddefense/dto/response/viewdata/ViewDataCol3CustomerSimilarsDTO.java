package br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.response.viewdata;

import java.io.Serializable;

/**
 * @author douglas
 * @since 31/03/2017
 */
public class ViewDataCol3CustomerSimilarsDTO implements Serializable {

    private static final long serialVersionUID = 7146299459749864711L;

    private Integer id;
    private String similarity;
    private String pending;
    private String name;
    private String identifier_code;
    private String insert_date;
    private String insert_time;
    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSimilarity() {
        return similarity;
    }

    public void setSimilarity(String similarity) {
        this.similarity = similarity;
    }

    public String getPending() {
        return pending;
    }

    public void setPending(String pending) {
        this.pending = pending;
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
