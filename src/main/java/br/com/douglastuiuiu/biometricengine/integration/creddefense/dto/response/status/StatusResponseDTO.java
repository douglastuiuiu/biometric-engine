package br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.response.status;

import br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.response.ResponseDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * @author douglas
 * @since 20/03/2017
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class StatusResponseDTO extends ResponseDTO implements Serializable {

    private static final long serialVersionUID = 2801602875316579252L;

    private StatusCreditDTO creditrequest;

    public StatusCreditDTO getCreditrequest() {
        return creditrequest;
    }

    public void setCreditrequest(StatusCreditDTO creditrequest) {
        this.creditrequest = creditrequest;
    }

}
