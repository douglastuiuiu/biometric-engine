package br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.response.changestatus;

import br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.response.ResponseDTO;
import br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.response.include.AuthenticationResponseDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * @author douglas
 * @since 20/03/2017
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChangeStatusResponseDTO extends ResponseDTO implements Serializable {

    private static final long serialVersionUID = 1826561941624314734L;

    private ChangeStatusCreditDTO creditrequest;
    private AuthenticationResponseDTO authentication;

    public ChangeStatusCreditDTO getCreditrequest() {
        return creditrequest;
    }

    public void setCreditrequest(ChangeStatusCreditDTO creditrequest) {
        this.creditrequest = creditrequest;
    }

    public AuthenticationResponseDTO getAuthentication() {
        return authentication;
    }

    public void setAuthentication(AuthenticationResponseDTO authentication) {
        this.authentication = authentication;
    }

}
