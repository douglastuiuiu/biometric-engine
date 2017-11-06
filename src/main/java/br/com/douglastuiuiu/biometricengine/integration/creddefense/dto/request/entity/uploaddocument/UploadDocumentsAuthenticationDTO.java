package br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.request.entity.uploaddocument;

import java.io.Serializable;

/**
 * @author douglas
 * @since 20/03/2017
 */
public class UploadDocumentsAuthenticationDTO implements Serializable {

    private static final long serialVersionUID = -139787883018611002L;

    private String token;

    public UploadDocumentsAuthenticationDTO() {
        super();
    }

    public UploadDocumentsAuthenticationDTO(String token) {
        super();
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
