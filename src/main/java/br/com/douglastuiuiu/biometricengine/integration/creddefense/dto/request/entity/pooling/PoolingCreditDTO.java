package br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.request.entity.pooling;

import java.io.Serializable;

/**
 * @author douglas
 * @since 20/03/2017
 */
public class PoolingCreditDTO implements Serializable {

    private static final long serialVersionUID = 5266562231387625409L;

    private Integer id;

    public PoolingCreditDTO() {
    }

    public PoolingCreditDTO(Integer id) {
        this.setId(id);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
