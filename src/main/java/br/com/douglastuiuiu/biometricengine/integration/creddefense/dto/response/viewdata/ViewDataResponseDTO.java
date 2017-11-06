package br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.response.viewdata;

import br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.response.ResponseDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * @author douglas
 * @since 20/03/2017
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ViewDataResponseDTO extends ResponseDTO implements Serializable {

    private static final long serialVersionUID = 3656245152545933299L;

    private ViewDataCreditDTO creditrequest;

    public ViewDataCreditDTO getCreditrequest() {
        return creditrequest;
    }

    public void setCreditrequest(ViewDataCreditDTO creditrequest) {
        this.creditrequest = creditrequest;
    }

}
