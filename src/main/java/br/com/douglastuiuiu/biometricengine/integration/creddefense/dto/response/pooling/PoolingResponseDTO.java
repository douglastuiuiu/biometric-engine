package br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.response.pooling;

import br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.response.ResponseDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Transient;

import java.io.Serializable;

/**
 * @author douglas
 * @since 20/03/2017
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PoolingResponseDTO extends ResponseDTO implements Serializable {

    private static final long serialVersionUID = -6725042344102646107L;

    @Transient
    private String id;
    private PoolingCreditDTO creditrequest;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PoolingCreditDTO getCreditrequest() {
        return creditrequest;
    }

    public void setCreditrequest(PoolingCreditDTO creditrequest) {
        this.creditrequest = creditrequest;
    }

}
