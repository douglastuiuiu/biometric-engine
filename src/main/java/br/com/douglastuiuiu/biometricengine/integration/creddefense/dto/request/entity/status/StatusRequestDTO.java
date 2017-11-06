package br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.request.entity.status;

import java.io.Serializable;

/**
 * @author douglas
 * @since 20/03/2017
 */
public class StatusRequestDTO implements Serializable {

    private static final long serialVersionUID = -5996201762275552338L;

    private StatusCreditDTO creditrequest;
    private StatusAuthenticationDTO authentication;

    public StatusRequestDTO() {
    }

    public StatusRequestDTO(StatusCreditDTO creditrequest, StatusAuthenticationDTO authentication) {
        super();
        this.creditrequest = creditrequest;
        this.authentication = authentication;
    }

    public StatusCreditDTO getCreditrequest() {
        return creditrequest;
    }

    public void setCreditrequest(StatusCreditDTO creditrequest) {
        this.creditrequest = creditrequest;
    }

    public StatusAuthenticationDTO getAuthentication() {
        return authentication;
    }

    public void setAuthentication(StatusAuthenticationDTO authentication) {
        this.authentication = authentication;
    }
}
