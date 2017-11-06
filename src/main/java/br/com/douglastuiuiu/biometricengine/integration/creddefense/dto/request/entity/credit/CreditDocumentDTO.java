package br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.request.entity.credit;

import java.io.Serializable;

/**
 * @author douglas
 * @since 20/03/2017
 */
public class CreditDocumentDTO implements Serializable {

    private static final long serialVersionUID = -3664504376327678625L;

    private Integer type;
    private String image;

    public CreditDocumentDTO() {
    }

    public CreditDocumentDTO(Integer type, String image) {
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
