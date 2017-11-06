package br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.request.entity.uploaddocument;

import java.io.Serializable;
import java.util.List;

/**
 * @author douglas
 * @since 20/03/2017
 */
public class UploadDocumentsCreditDTO implements Serializable {

    private static final long serialVersionUID = -4986647775921284963L;

    private Integer id;
    private List<UploadDocumentsDocumentDTO> document;

    public UploadDocumentsCreditDTO() {
    }

    public UploadDocumentsCreditDTO(Integer id, List<UploadDocumentsDocumentDTO> document) {
        this.id = id;
        this.document = document;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<UploadDocumentsDocumentDTO> getDocument() {
        return document;
    }

    public void setDocument(List<UploadDocumentsDocumentDTO> document) {
        this.document = document;
    }
}
