package br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.response.changestatus;

import java.io.Serializable;

/**
 * @author douglas
 * @since 20/03/2017
 */
public class ChangeStatusCreditDTO implements Serializable {

    private static final long serialVersionUID = 3226258583067490566L;

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
