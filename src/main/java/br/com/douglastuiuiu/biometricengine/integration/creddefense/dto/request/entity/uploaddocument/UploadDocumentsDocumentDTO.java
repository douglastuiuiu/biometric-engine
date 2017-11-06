package br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.request.entity.uploaddocument;

import java.io.Serializable;

/**
 * @author douglas
 * @since 20/03/2017
 */
public class UploadDocumentsDocumentDTO implements Serializable {

    private static final long serialVersionUID = 8575540560360573429L;

    private Integer type;
    private String image;

    public UploadDocumentsDocumentDTO() {
    }

    public UploadDocumentsDocumentDTO(Integer type, String image) {
        super();
        this.type = type;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

}
