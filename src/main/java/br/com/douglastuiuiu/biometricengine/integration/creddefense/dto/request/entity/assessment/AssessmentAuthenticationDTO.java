package br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.request.entity.assessment;

import java.io.Serializable;

/**
 * @author douglas
 * @since 20/03/2017
 */
public class AssessmentAuthenticationDTO implements Serializable {

    private static final long serialVersionUID = 5081936737727176650L;

    private String token;

    public AssessmentAuthenticationDTO() {
        super();
    }

    public AssessmentAuthenticationDTO(String token) {
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
