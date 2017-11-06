package br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.request.entity.viewdata;

import java.io.Serializable;

/**
 * @author douglas
 * @since 20/03/2017
 */
public class ViewDataAuthenticationDTO implements Serializable {

    private static final long serialVersionUID = 2673965196459258748L;

    private String token;

    public ViewDataAuthenticationDTO() {
        super();
    }

    public ViewDataAuthenticationDTO(String token) {
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
