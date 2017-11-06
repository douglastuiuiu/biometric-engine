package br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.response.pooling;

import java.io.Serializable;
import java.util.List;

/**
 * @author douglas
 * @since 31/03/2017
 */
public class PoolingCol3DTO implements Serializable {

    private static final long serialVersionUID = -3647156372465376924L;

    private List<PoolingCol3CreditRequestSimilarsDTO> creditrequest_similars;
    private List<PoolingCol3CustomerSimilarsDTO> customer_similars;

    public List<PoolingCol3CreditRequestSimilarsDTO> getCreditrequest_similars() {
        return creditrequest_similars;
    }

    public void setCreditrequest_similars(List<PoolingCol3CreditRequestSimilarsDTO> creditrequest_similars) {
        this.creditrequest_similars = creditrequest_similars;
    }

    public List<PoolingCol3CustomerSimilarsDTO> getCustomer_similars() {
        return customer_similars;
    }

    public void setCustomer_similars(List<PoolingCol3CustomerSimilarsDTO> customer_similars) {
        this.customer_similars = customer_similars;
    }
}
