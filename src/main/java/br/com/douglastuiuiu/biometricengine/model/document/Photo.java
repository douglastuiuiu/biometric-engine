package br.com.douglastuiuiu.biometricengine.model.document;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author douglas
 * @since 23/03/2017
 */
@Document(collection = "photo")
public class Photo extends File {

    public Photo() {

    }

    public Photo(String fileName) {
        setName(fileName);
    }

}
