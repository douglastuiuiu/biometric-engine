package br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.request.entity.viewdocument;

import java.io.Serializable;

/**
 * @author douglas
 * @since 20/03/2017
 */
public class ViewDocumentsCreditDTO implements Serializable {

    private static final long serialVersionUID = 3145826967161970564L;

    private Integer id;

    public ViewDocumentsCreditDTO() {
    }

    public ViewDocumentsCreditDTO(Integer id) {
        this.setId(id);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
