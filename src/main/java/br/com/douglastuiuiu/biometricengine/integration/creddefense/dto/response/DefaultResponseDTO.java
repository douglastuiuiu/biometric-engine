package br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.response;

import br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.response.include.AssessmentResponseDTO;
import br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.response.include.AuthenticationResponseDTO;
import br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.response.include.CreditResponseDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

/**
 * @author douglas
 * @since 20/03/2017
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DefaultResponseDTO extends ResponseDTO implements Serializable {

    private static final long serialVersionUID = -8771257576759855802L;

    private List<CreditResponseDTO> creditrequest;
    private AuthenticationResponseDTO authentication;
    private List<AssessmentResponseDTO> assessment;

    public List<CreditResponseDTO> getCreditrequest() {
        return creditrequest;
    }

    public void setCreditrequest(List<CreditResponseDTO> creditrequest) {
        this.creditrequest = creditrequest;
    }

    public AuthenticationResponseDTO getAuthentication() {
        return authentication;
    }

    public void setAuthentication(AuthenticationResponseDTO authentication) {
        this.authentication = authentication;
    }

    public List<AssessmentResponseDTO> getAssessment() {
        return assessment;
    }

    public void setAssessment(List<AssessmentResponseDTO> assessment) {
        this.assessment = assessment;
    }
}
