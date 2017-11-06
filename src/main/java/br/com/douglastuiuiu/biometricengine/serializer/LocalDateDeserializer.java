package br.com.douglastuiuiu.biometricengine.serializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author douglasg
 * @since 21/03/2017
 */
public class LocalDateDeserializer extends JsonDeserializer<LocalDate> {

    private static final String REGEX_DATA = "^(((0[1-9]|[12]\\d|3[01])\\/(0[13578]|1[02])\\/((19|[2-9]\\d)\\d{2}))|((0[1-9]|[12]\\d|30)\\/(0[13456789]|1[012])\\/((19|[2-9]\\d)\\d{2}))|((0[1-9]|1\\d|2[0-8])\\/02\\/((19|[2-9]\\d)\\d{2}))|(29\\/02\\/((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))))$";

    @Override
    public LocalDate deserialize(JsonParser jsonParse, DeserializationContext context) throws IOException {
        if (jsonParse.getText().matches(REGEX_DATA)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return LocalDate.parse(jsonParse.getText(), formatter);
        } else {
            return LocalDate.parse(jsonParse.getText());
        }
    }
}