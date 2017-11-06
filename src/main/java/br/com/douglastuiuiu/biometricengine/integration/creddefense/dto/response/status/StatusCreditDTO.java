package br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.response.status;

import java.io.Serializable;

/**
 * @author douglas
 * @since 20/03/2017
 */
public class StatusCreditDTO implements Serializable {

    private static final long serialVersionUID = -126613989931625508L;

    private Integer id;
    private String status;

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

}
