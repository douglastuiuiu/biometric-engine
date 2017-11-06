package br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.request.entity.pooling;

import java.io.Serializable;

/**
 * @author douglas
 * @since 20/03/2017
 */
public class PoolingAuthenticationDTO implements Serializable {

    private static final long serialVersionUID = -4283838548556040073L;

    private String token;

    public PoolingAuthenticationDTO() {
        super();
    }

    public PoolingAuthenticationDTO(String token) {
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
