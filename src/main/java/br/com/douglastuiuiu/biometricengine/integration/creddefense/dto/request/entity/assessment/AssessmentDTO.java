package br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.request.entity.assessment;

import java.io.Serializable;

/**
 * @author douglas
 * @since 20/03/2017
 */
public class AssessmentDTO implements Serializable {

    private static final long serialVersionUID = -6191266487455897619L;

    private String photo;

    public AssessmentDTO() {
        super();
    }

    public AssessmentDTO(String photo) {
        super();
        this.setPhoto(photo);
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

}
