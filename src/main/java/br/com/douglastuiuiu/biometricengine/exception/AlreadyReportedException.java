package br.com.douglastuiuiu.biometricengine.exception;

import br.com.douglastuiuiu.biometricengine.model.document.AnalyzeRequest;

/**
 * @author douglasg
 * @since 10/03/2017
 */
public class AlreadyReportedException extends ServiceException {

    private static final long serialVersionUID = 4138486094068893119L;

    private AnalyzeRequest body;

    public AlreadyReportedException() {
        super();
    }

    public AlreadyReportedException(String message, Throwable cause) {
        super(message, cause);
    }

    public AlreadyReportedException(String message) {
        super(message);
    }

    public AlreadyReportedException(Throwable cause) {
        super(cause);
    }

    public AnalyzeRequest getBody() {
        return body;
    }
}
