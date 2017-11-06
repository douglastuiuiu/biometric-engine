package br.com.douglastuiuiu.biometricengine.exception;

/**
 * @author douglas
 * @since 20/03/2017
 */
public class IntegrationException extends Exception {

    private static final long serialVersionUID = 5915274651851409112L;

    public IntegrationException(String message, Throwable cause) {
        super(message, cause);
    }

    public IntegrationException(String message) {
        super(message);
    }

}
