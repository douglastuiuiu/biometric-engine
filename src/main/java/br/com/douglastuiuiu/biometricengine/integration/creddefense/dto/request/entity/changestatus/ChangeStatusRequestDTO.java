package br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.request.entity.changestatus;

import java.io.Serializable;

/**
 * @author douglas
 * @since 20/03/2017
 */
public class ChangeStatusRequestDTO implements Serializable {

    private static final long serialVersionUID = -7664713686186936270L;

    private ChangeStatusCreditDTO creditrequest;
    private ChangeStatusAuthenticationDTO authentication;

    public ChangeStatusRequestDTO() {
    }

    public ChangeStatusRequestDTO(ChangeStatusCreditDTO creditrequest, ChangeStatusAuthenticationDTO authentication) {
        super();
        this.creditrequest = creditrequest;
        this.authentication = authentication;
    }

    public ChangeStatusCreditDTO getCreditrequest() {
        return creditrequest;
    }

    public void setCreditrequest(ChangeStatusCreditDTO creditrequest) {
        this.creditrequest = creditrequest;
    }

    public ChangeStatusAuthenticationDTO getAuthentication() {
        return authentication;
    }

    public void setAuthentication(ChangeStatusAuthenticationDTO authentication) {
        this.authentication = authentication;
    }
}
