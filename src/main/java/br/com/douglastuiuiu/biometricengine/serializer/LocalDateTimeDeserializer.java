package br.com.douglastuiuiu.biometricengine.serializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * @author douglasg
 * @since 10/03/2017
 */
public class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {
    @Override
    public LocalDateTime deserialize(JsonParser jsonParse, DeserializationContext context) throws IOException, JsonProcessingException {
        return LocalDateTime.parse(jsonParse.getText());
    }
}