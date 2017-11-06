package br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.response;

import br.com.douglastuiuiu.biometricengine.exception.IntegrationException;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

/**
 * @author douglas
 * @since 20/03/2017
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseDTO implements Serializable {

    private static final long serialVersionUID = 4328677806147487225L;

    private static final String ERROR = "error";
    private String result;
    private String code;
    private List<String> messages;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ResponseDTO responseValidate() throws IntegrationException {
        if ((getCode() != null && getCode().equalsIgnoreCase(ERROR)) || (getResult() != null && getResult().equalsIgnoreCase(ERROR))) {
            String error;
            if (getMessages().size() > 0) {
                error = getMessages().get(0);
            } else {
                error = (getCode() != null ? getCode() : getResult());
            }
            throw new IntegrationException(error);
        } else {
            return this;
        }
    }

}
