package br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.request.entity.credit;

import java.io.Serializable;

/**
 * @author douglas
 * @since 20/03/2017
 */
public class CreditAuthenticationDTO implements Serializable {

    private static final long serialVersionUID = 8837074849567192071L;

    private String token;

    public CreditAuthenticationDTO() {
        super();
    }

    public CreditAuthenticationDTO(String token) {
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
