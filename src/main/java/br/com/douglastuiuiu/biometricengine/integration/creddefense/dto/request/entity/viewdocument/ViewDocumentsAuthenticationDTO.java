package br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.request.entity.viewdocument;

import java.io.Serializable;

/**
 * @author douglas
 * @since 20/03/2017
 */
public class ViewDocumentsAuthenticationDTO implements Serializable {

    private static final long serialVersionUID = -3812276539940583489L;

    private String token;

    public ViewDocumentsAuthenticationDTO() {
        super();
    }

    public ViewDocumentsAuthenticationDTO(String token) {
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
