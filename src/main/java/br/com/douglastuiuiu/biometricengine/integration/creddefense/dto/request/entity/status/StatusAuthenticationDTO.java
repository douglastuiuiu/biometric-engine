package br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.request.entity.status;

import java.io.Serializable;

/**
 * @author douglas
 * @since 20/03/2017
 */
public class StatusAuthenticationDTO implements Serializable {

    private static final long serialVersionUID = 7693622246112262942L;

    private String token;

    public StatusAuthenticationDTO() {
        super();
    }

    public StatusAuthenticationDTO(String token) {
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
