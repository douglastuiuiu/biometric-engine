package br.com.douglastuiuiu.biometricengine.exception;

/**
 * @author douglasg
 * @since 10/03/2017
 */
public class ServiceException extends Exception {

    private static final long serialVersionUID = -1766164263398361960L;

    public ServiceException() {
        super();
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

}
