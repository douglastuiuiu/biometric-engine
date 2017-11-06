package br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.request.entity.viewdocument;

import java.io.Serializable;

/**
 * @author douglas
 * @since 20/03/2017
 */
public class ViewDocumentsRequestDTO implements Serializable {

    private static final long serialVersionUID = -7096093808586123929L;

    private ViewDocumentsCreditDTO creditrequest;
    private ViewDocumentsAuthenticationDTO authentication;

    public ViewDocumentsRequestDTO() {
    }

    public ViewDocumentsRequestDTO(ViewDocumentsCreditDTO creditrequest, ViewDocumentsAuthenticationDTO authentication) {
        super();
        this.creditrequest = creditrequest;
        this.authentication = authentication;
    }

    public ViewDocumentsCreditDTO getCreditrequest() {
        return creditrequest;
    }

    public void setCreditrequest(ViewDocumentsCreditDTO creditrequest) {
        this.creditrequest = creditrequest;
    }

    public ViewDocumentsAuthenticationDTO getAuthentication() {
        return authentication;
    }

    public void setAuthentication(ViewDocumentsAuthenticationDTO authentication) {
        this.authentication = authentication;
    }
}
