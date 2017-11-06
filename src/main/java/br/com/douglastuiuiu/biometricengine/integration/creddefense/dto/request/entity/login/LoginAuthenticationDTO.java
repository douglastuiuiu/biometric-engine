package br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.request.entity.login;

import java.io.Serializable;

/**
 * @author douglas
 * @since 20/03/2017
 */
public class LoginAuthenticationDTO implements Serializable {

    private static final long serialVersionUID = -7705012376111324986L;

    private String username;
    private String password;

    public LoginAuthenticationDTO() {
        super();
    }

    public LoginAuthenticationDTO(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
