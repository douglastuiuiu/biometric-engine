package br.com.douglastuiuiu.biometricengine.model.dto;

import br.com.douglastuiuiu.biometricengine.model.document.Document;
import br.com.douglastuiuiu.biometricengine.model.enumeration.DocumentTypeEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

/**
 * @author douglas
 * @since 21/03/2017
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class DocumentDTO implements Serializable {

    private static final long serialVersionUID = -7111715412018599285L;

    private String name;
    @NotNull
    private DocumentTypeEnum type;
    @NotEmpty
    private String base64;
    private LinkDTO link;

    public static DocumentDTO parseDocumentDTO(Document document) {
        DocumentDTO documentDTO = new DocumentDTO();
        documentDTO.setName(document.getName());
        documentDTO.setType(document.getType());
        return documentDTO;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DocumentTypeEnum getType() {
        return type;
    }

    public void setType(DocumentTypeEnum type) {
        this.type = type;
    }

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }

    public LinkDTO getLink() {
        return link;
    }

    public void setLink(LinkDTO link) {
        this.link = link;
    }

}
