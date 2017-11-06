package br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.response.viewdata;

import java.io.Serializable;

/**
 * @author douglas
 * @since 20/03/2017
 */
public class ViewDataCol1DTO implements Serializable {

    private static final long serialVersionUID = -3246646008601486757L;

    private ViewDataCol1CreditRequestDTO creditrequest;

    public ViewDataCol1CreditRequestDTO getCreditrequest() {
        return creditrequest;
    }

    public void setCreditrequest(ViewDataCol1CreditRequestDTO creditrequest) {
        this.creditrequest = creditrequest;
    }
}
