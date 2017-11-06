package br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.request.entity.logout;

import java.io.Serializable;

/**
 * @author douglas
 * @since 20/03/2017
 */
public class LogoutRequestDTO implements Serializable {

    private static final long serialVersionUID = 2599302246277883004L;

    private LogoutAuthenticationDTO authentication;

    public LogoutRequestDTO() {
    }

    public LogoutRequestDTO(LogoutAuthenticationDTO authentication) {
        super();
        this.authentication = authentication;
    }

    public LogoutAuthenticationDTO getAuthentication() {
        return authentication;
    }

    public void setAuthentication(LogoutAuthenticationDTO authentication) {
        this.authentication = authentication;
    }
}
