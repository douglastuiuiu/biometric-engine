package br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.response.viewdocuments;

import br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.response.include.DocumentResponseDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

/**
 * @author douglas
 * @since 20/03/2017
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ViewDocumentsCreditResponseDTO implements Serializable {

    private static final long serialVersionUID = -3138036651449510885L;

    private List<DocumentResponseDTO> documents;

    public List<DocumentResponseDTO> getDocuments() {
        return documents;
    }

    public void setDocuments(List<DocumentResponseDTO> documents) {
        this.documents = documents;
    }
}
