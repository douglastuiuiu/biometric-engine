package br.com.douglastuiuiu.biometricengine.resource.handler;

import br.com.douglastuiuiu.biometricengine.exception.ConflictException;
import br.com.douglastuiuiu.biometricengine.exception.NotFoundException;
import br.com.douglastuiuiu.biometricengine.exception.NotProcessedException;
import br.com.douglastuiuiu.biometricengine.exception.ServiceException;
import br.com.douglastuiuiu.biometricengine.util.ApiUtil;
import br.com.douglastuiuiu.biometricengine.util.MessageLocale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

/**
 * @author douglasg
 * @since 10/03/2017
 */
@ControllerAdvice(annotations = RestController.class)
public class ApiValidatorExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(ApiValidatorExceptionHandler.class);

    @Autowired
    private MessageLocale messageLocale;

    /**
     * Erros gerados pela validação dos dados da requisição
     *
     * @param error
     * @return
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException error) {
        logger.error("MethodArgumentNotValidException: " + error, error);
        return ApiUtil.responseErrorValidation(error);
    }

    /**
     * Erros relacionados a regras de negócio
     *
     * @param error
     * @return
     */
    @ExceptionHandler(value = ServiceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<Object> handleServiceException(ServiceException error) {
        logger.error("ServiceException: " + error, error);
        return ApiUtil.responseError(error.getMessage(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Validação de itens não existentes ou não encontrados
     *
     * @param error
     * @return
     */
    @ExceptionHandler(value = NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ResponseEntity<Object> handleNotFoundException(NotFoundException error) {
        logger.error("notFoundException: " + error, error);
        return ApiUtil.responseError(error.getMessage(), HttpStatus.NOT_FOUND);
    }

    /**
     * Validação para quando tenta-se atualizar um registro com um status que não permite essa atualização
     *
     * @param error
     * @return
     */
    @ExceptionHandler(value = ConflictException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public ResponseEntity<Object> handleConflictException(ConflictException error) {
        logger.error("conflictException: " + error, error);
        return ApiUtil.responseError(error.getMessage(), HttpStatus.CONFLICT);
    }

    /**
     * Validação para quando tenta-se buscar o resultado de uma análise que ainda não foi processada pelo bureau
     *
     * @param error
     * @return
     */
    @ExceptionHandler(value = NotProcessedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<Object> handleNotProcessedException(NotProcessedException error) {
        logger.error("notProcessedException: " + error, error);
        return ApiUtil.responseError(error.getMessage(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Erros relacionado ao envio de dados com formato ou tipo inválido
     *
     * @param error
     * @return
     */
    @ExceptionHandler(value = HttpMessageConversionException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseEntity<Object> handleException(HttpMessageConversionException error) {
        logger.error("HttpMessageConversionException: " + error, error);
        return ApiUtil.responseError(error.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Pega Qualquer outro erro não tratado
     *
     * @param error
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseEntity<Object> handleException(Exception error) {
        logger.error("Erro não tratado: " + error, error);
        return ApiUtil.responseError(messageLocale.getMessage("error.generic_error"), HttpStatus.BAD_REQUEST);
    }
}
