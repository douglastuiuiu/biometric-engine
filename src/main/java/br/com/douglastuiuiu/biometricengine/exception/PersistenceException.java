package br.com.douglastuiuiu.biometricengine.exception;

/**
 * @author douglas
 * @since 20/03/2017
 */
public class PersistenceException extends Exception {

    private static final long serialVersionUID = 2556082857136310152L;

    public PersistenceException() {
        super();
    }

    public PersistenceException(String message, Throwable cause) {
        super(message, cause);
    }

    public PersistenceException(String message) {
        super(message);
    }

    public PersistenceException(Throwable cause) {
        super(cause);
    }

}
