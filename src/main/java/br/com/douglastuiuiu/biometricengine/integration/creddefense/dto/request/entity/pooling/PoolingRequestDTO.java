package br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.request.entity.pooling;

import java.io.Serializable;

/**
 * @author douglas
 * @since 20/03/2017
 */
public class PoolingRequestDTO implements Serializable {

    private static final long serialVersionUID = -5081278523839761332L;

    private PoolingCreditDTO creditrequest;
    private PoolingAuthenticationDTO authentication;

    public PoolingRequestDTO() {
    }

    public PoolingRequestDTO(PoolingCreditDTO creditrequest, PoolingAuthenticationDTO authentication) {
        super();
        this.creditrequest = creditrequest;
        this.authentication = authentication;
    }

    public PoolingCreditDTO getCreditrequest() {
        return creditrequest;
    }

    public void setCreditrequest(PoolingCreditDTO creditrequest) {
        this.creditrequest = creditrequest;
    }

    public PoolingAuthenticationDTO getAuthentication() {
        return authentication;
    }

    public void setAuthentication(PoolingAuthenticationDTO authentication) {
        this.authentication = authentication;
    }
}
