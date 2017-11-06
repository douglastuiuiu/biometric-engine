package br.com.douglastuiuiu.biometricengine.exception;

/**
 * @author douglasg
 * @since 24/03/2017
 */
public class ConflictException extends ServiceException {

    private static final long serialVersionUID = -2471165868047841950L;

    public ConflictException() {
        super();
    }

    public ConflictException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConflictException(String message) {
        super(message);
    }

    public ConflictException(Throwable cause) {
        super(cause);
    }
}
