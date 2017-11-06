package br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.request.entity.changestatus;

import java.io.Serializable;

/**
 * @author douglas
 * @since 20/03/2017
 */
public class ChangeStatusCreditDTO implements Serializable {

    private static final long serialVersionUID = -1656074471804620371L;

    private Integer id;
    private String status;
    private String comment;

    public ChangeStatusCreditDTO() {
    }

    public ChangeStatusCreditDTO(Integer id, String status, String comment) {
        this.id = id;
        this.status = status;
        this.comment = comment;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
