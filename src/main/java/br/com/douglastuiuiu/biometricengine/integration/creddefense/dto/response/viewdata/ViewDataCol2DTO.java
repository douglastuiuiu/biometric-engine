package br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.response.viewdata;

import java.io.Serializable;
import java.util.List;

/**
 * @author douglas
 * @since 31/03/2017
 */
public class ViewDataCol2DTO implements Serializable {

    private static final long serialVersionUID = 3757967726177574456L;

    private ViewDataCol2CustomerDTO customer;
    private List<ViewDataCol2FraudDTO> fraud;

    public ViewDataCol2CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(ViewDataCol2CustomerDTO customer) {
        this.customer = customer;
    }

    public List<ViewDataCol2FraudDTO> getFraud() {
        return fraud;
    }

    public void setFraud(List<ViewDataCol2FraudDTO> fraud) {
        this.fraud = fraud;
    }
}
