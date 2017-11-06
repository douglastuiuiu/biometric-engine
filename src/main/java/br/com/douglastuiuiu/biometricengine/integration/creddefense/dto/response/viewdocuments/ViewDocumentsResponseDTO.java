package br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.response.viewdocuments;

import br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.response.ResponseDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * @author douglas
 * @since 20/03/2017
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ViewDocumentsResponseDTO extends ResponseDTO implements Serializable {

    private static final long serialVersionUID = -8771257576759855802L;

    private ViewDocumentsCreditResponseDTO creditrequest;

    public ViewDocumentsCreditResponseDTO getCreditrequest() {
        return creditrequest;
    }

    public void setCreditrequest(ViewDocumentsCreditResponseDTO creditrequest) {
        this.creditrequest = creditrequest;
    }
}
