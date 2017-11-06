package br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.request.entity.viewdata;

import java.io.Serializable;

/**
 * @author douglas
 * @since 20/03/2017
 */
public class ViewDataRequestDTO implements Serializable {

    private static final long serialVersionUID = -6002594667423536871L;

    private ViewDataCreditDTO creditrequest;
    private ViewDataAuthenticationDTO authentication;

    public ViewDataRequestDTO() {
    }

    public ViewDataRequestDTO(ViewDataCreditDTO creditrequest, ViewDataAuthenticationDTO authentication) {
        super();
        this.creditrequest = creditrequest;
        this.authentication = authentication;
    }

    public ViewDataCreditDTO getCreditrequest() {
        return creditrequest;
    }

    public void setCreditrequest(ViewDataCreditDTO creditrequest) {
        this.creditrequest = creditrequest;
    }

    public ViewDataAuthenticationDTO getAuthentication() {
        return authentication;
    }

    public void setAuthentication(ViewDataAuthenticationDTO authentication) {
        this.authentication = authentication;
    }
}
