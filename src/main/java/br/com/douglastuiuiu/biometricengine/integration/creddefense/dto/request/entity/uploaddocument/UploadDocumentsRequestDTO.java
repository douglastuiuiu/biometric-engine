package br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.request.entity.uploaddocument;

import java.io.Serializable;

/**
 * @author douglas
 * @since 20/03/2017
 */
public class UploadDocumentsRequestDTO implements Serializable {

    private static final long serialVersionUID = -4520266877047727072L;

    private UploadDocumentsCreditDTO creditrequest;
    private UploadDocumentsAuthenticationDTO authentication;

    public UploadDocumentsRequestDTO() {
    }

    public UploadDocumentsRequestDTO(UploadDocumentsCreditDTO creditrequest, UploadDocumentsAuthenticationDTO authentication) {
        super();
        this.creditrequest = creditrequest;
        this.authentication = authentication;
    }

    public UploadDocumentsCreditDTO getCreditrequest() {
        return creditrequest;
    }

    public void setCreditrequest(UploadDocumentsCreditDTO creditrequest) {
        this.creditrequest = creditrequest;
    }

    public UploadDocumentsAuthenticationDTO getAuthentication() {
        return authentication;
    }

    public void setAuthentication(UploadDocumentsAuthenticationDTO authentication) {
        this.authentication = authentication;
    }
}
