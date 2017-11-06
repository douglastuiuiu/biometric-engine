package br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.request.entity.changestatus;

import java.io.Serializable;

/**
 * @author douglas
 * @since 20/03/2017
 */
public class ChangeStatusAuthenticationDTO implements Serializable {

    private static final long serialVersionUID = -8437294333871523419L;

    private String token;

    public ChangeStatusAuthenticationDTO() {
        super();
    }

    public ChangeStatusAuthenticationDTO(String token) {
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
