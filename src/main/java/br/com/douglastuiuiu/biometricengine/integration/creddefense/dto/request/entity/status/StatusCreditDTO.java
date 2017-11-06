package br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.request.entity.status;

import java.io.Serializable;

/**
 * @author douglas
 * @since 20/03/2017
 */
public class StatusCreditDTO implements Serializable {

    private static final long serialVersionUID = -6048526730165007710L;

    private Integer id;

    public StatusCreditDTO() {
    }

    public StatusCreditDTO(Integer id) {
        this.setId(id);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
