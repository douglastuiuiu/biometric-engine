package br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.response.pooling;

import java.io.Serializable;
import java.util.List;

/**
 * @author douglas
 * @since 31/03/2017
 */
public class PoolingCol2DTO implements Serializable {

    private static final long serialVersionUID = -5708691728856608345L;

    private PoolingCol2CustomerDTO customer;
    private List<PoolingCol2FraudDTO> fraud;

    public PoolingCol2CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(PoolingCol2CustomerDTO customer) {
        this.customer = customer;
    }

    public List<PoolingCol2FraudDTO> getFraud() {
        return fraud;
    }

    public void setFraud(List<PoolingCol2FraudDTO> fraud) {
        this.fraud = fraud;
    }
}
