package br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.request.entity.logout;

import java.io.Serializable;

/**
 * @author douglas
 * @since 20/03/2017
 */
public class LogoutAuthenticationDTO implements Serializable {

    private static final long serialVersionUID = -2737099332198275671L;

    private String token;

    public LogoutAuthenticationDTO() {
        super();
    }

    public LogoutAuthenticationDTO(String token) {
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
