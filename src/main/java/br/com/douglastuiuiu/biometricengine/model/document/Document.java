package br.com.douglastuiuiu.biometricengine.model.document;

import br.com.douglastuiuiu.biometricengine.model.dto.DocumentDTO;
import br.com.douglastuiuiu.biometricengine.model.enumeration.DocumentTypeEnum;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author douglas
 * @since 23/03/2017
 */
@org.springframework.data.mongodb.core.mapping.Document(collection = "document")
public class Document extends File {

    @Field
    private DocumentTypeEnum type;

    public Document(DocumentTypeEnum type) {
        setType(type);
    }

    public static Document parseDocument(DocumentDTO documentDTO) {
        Document document = new Document(documentDTO.getType());
        return document;
    }

    public DocumentTypeEnum getType() {
        return type;
    }

    public void setType(DocumentTypeEnum type) {
        this.type = type;
    }

}
