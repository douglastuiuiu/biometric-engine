package br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.request.entity.assessment;

import java.io.Serializable;
import java.util.List;

/**
 * @author douglas
 * @since 20/03/2017
 */
public class AssessmentRequestDTO implements Serializable {

    private static final long serialVersionUID = 3378635554378899631L;

    private List<AssessmentDTO> assessment;
    private AssessmentAuthenticationDTO authentication;

    public AssessmentRequestDTO() {
    }

    public AssessmentRequestDTO(List<AssessmentDTO> assessment, AssessmentAuthenticationDTO authentication) {
        super();
        this.assessment = assessment;
        this.authentication = authentication;
    }

    public List<AssessmentDTO> getAssessment() {
        return assessment;
    }

    public void setAssessment(List<AssessmentDTO> assessment) {
        this.assessment = assessment;
    }

    public AssessmentAuthenticationDTO getAuthentication() {
        return authentication;
    }

    public void setAuthentication(AssessmentAuthenticationDTO authentication) {
        this.authentication = authentication;
    }
}
