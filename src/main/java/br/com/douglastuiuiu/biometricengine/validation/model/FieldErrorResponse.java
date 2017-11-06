package br.com.douglastuiuiu.biometricengine.validation.model;

/**
 * @author douglasg
 * @since 10/03/2017
 */
public class FieldErrorResponse {

    private String fieldName;
    private String defaultMessage;
    private Object typeRejected;
    private Object valueRejected;

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getDefaultMessage() {
        return defaultMessage;
    }

    public void setDefaultMessage(String defaultMessage) {
        this.defaultMessage = defaultMessage;
    }

    public Object getTypeRejected() {
        return typeRejected;
    }

    public void setTypeRejected(Object typeRejected) {
        this.typeRejected = typeRejected;
    }

    public Object getValueRejected() {
        return valueRejected;
    }

    public void setValueRejected(Object valueRejected) {
        this.valueRejected = valueRejected;
    }

}
