package br.com.douglastuiuiu.biometricengine.validation.util;

import br.com.douglastuiuiu.biometricengine.validation.model.FieldErrorResponse;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author douglas
 * @since 21/03/2017
 */
public class ValidationUtil {

    private ValidationUtil() {
    }

    /**
     * Define o retorno de erro informando quais dados não passaram pela validação padrão do BeanValidation (JSR 303)
     *
     * @param error
     * @return
     */
    public static List<FieldErrorResponse> createFieldErrorResponse(MethodArgumentNotValidException error) {
        List<FieldErrorResponse> listErrors = new ArrayList<>();
        for (FieldError fieldError : error.getBindingResult().getFieldErrors()) {
            FieldErrorResponse validationResponse = new FieldErrorResponse();
            validationResponse.setFieldName(fieldError.getField());
            validationResponse.setTypeRejected(fieldError.getCode() != null ? fieldError.getCode().toString() : null);
            validationResponse.setValueRejected(fieldError.getRejectedValue() != null ? fieldError.getRejectedValue().toString() : null);
            validationResponse.setDefaultMessage(fieldError.getDefaultMessage());
            listErrors.add(validationResponse);
        }
        return listErrors;
    }
}