package br.com.douglastuiuiu.biometricengine.exception;

/**
 * @author douglasg
 * @since 24/03/2017
 */
public class NotFoundException extends ServiceException {

    private static final long serialVersionUID = -3741240331603825780L;

    public NotFoundException() {
        super();
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(Throwable cause) {
        super(cause);
    }
}
