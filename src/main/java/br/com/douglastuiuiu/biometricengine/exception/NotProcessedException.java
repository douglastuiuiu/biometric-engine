package br.com.douglastuiuiu.biometricengine.exception;

/**
 * @author douglasg
 * @since 24/03/2017
 */
public class NotProcessedException extends ServiceException {

    private static final long serialVersionUID = -5878052551686434438L;

    public NotProcessedException() {
        super();
    }

    public NotProcessedException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotProcessedException(String message) {
        super(message);
    }

    public NotProcessedException(Throwable cause) {
        super(cause);
    }
}
