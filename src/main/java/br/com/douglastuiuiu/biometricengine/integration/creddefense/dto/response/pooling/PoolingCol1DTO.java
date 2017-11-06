package br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.response.pooling;

import java.io.Serializable;

/**
 * @author douglas
 * @since 20/03/2017
 */
public class PoolingCol1DTO implements Serializable {

    private static final long serialVersionUID = 3544325374927970582L;

    private PoolingCol1CreditRequestDTO creditrequest;

    public PoolingCol1CreditRequestDTO getCreditrequest() {
        return creditrequest;
    }

    public void setCreditrequest(PoolingCol1CreditRequestDTO creditrequest) {
        this.creditrequest = creditrequest;
    }
}
