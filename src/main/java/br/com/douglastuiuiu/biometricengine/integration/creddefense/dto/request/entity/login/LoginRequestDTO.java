package br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.request.entity.login;

import java.io.Serializable;

/**
 * @author douglas
 * @since 20/03/2017
 */
public class LoginRequestDTO implements Serializable {

    private static final long serialVersionUID = 8526880202806233015L;

    private LoginAuthenticationDTO authentication;

    public LoginRequestDTO() {
    }

    public LoginRequestDTO(LoginAuthenticationDTO authentication) {
        super();
        this.authentication = authentication;
    }

    public LoginAuthenticationDTO getAuthentication() {
        return authentication;
    }

    public void setAuthentication(LoginAuthenticationDTO authentication) {
        this.authentication = authentication;
    }

}
