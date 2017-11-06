package br.com.douglastuiuiu.biometricengine.resource;

import br.com.douglastuiuiu.biometricengine.config.BiometricFactory;
import br.com.douglastuiuiu.biometricengine.exception.ServiceException;
import br.com.douglastuiuiu.biometricengine.model.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author douglas
 * @since 20/03/2017
 */
@RestController
@RequestMapping("/api/biometric/v1")
@CrossOrigin(origins = "*")
public class BiometricResource {

    @Autowired
    private BiometricFactory biometricFactory;

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<AnalyzeResponseDTO> createAnalyze(@RequestHeader(name = "biometricBureauName", required = true) String biometricBureauName,
                                                            @Valid @RequestBody AnalyzeRequestDTO analyzeRequestDTO) throws ServiceException {
        return biometricFactory.getObject().createAnalyzeRequest(analyzeRequestDTO);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Object> getAnalyze(@RequestHeader(name = "biometricBureauName", required = true) String biometricBureauName,
                                             @PathVariable("id") String analyzeRequestId) throws ServiceException {
        return biometricFactory.getObject().getAnalyze(analyzeRequestId);
    }

    @RequestMapping(value = "/{id}/photo", method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Object> updatePhoto(@RequestHeader(name = "biometricBureauName", required = true) String biometricBureauName,
                                              @PathVariable("id") String id, @Valid @RequestBody PhotoDTO photoDTO) throws ServiceException {
        AnalyzeResponseDTO analyzeResponseDTO = biometricFactory.getObject().updatePhoto(id, photoDTO);
        return new ResponseEntity<>(analyzeResponseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/fraud", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Object> setAnalyzeFraud(@RequestHeader(name = "biometricBureauName", required = true) String biometricBureauName,
                                                  @PathVariable("id") String idAnalyzeRequest, @Valid @RequestBody CommentDTO commentDTO) throws ServiceException {
        biometricFactory.getObject().setAnalyzeFraud(idAnalyzeRequest, commentDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Object> setAnalyzeFraud(@RequestHeader(name = "biometricBureauName", required = true) String biometricBureauName,
                                                  @PathVariable("id") String id) throws ServiceException {
        biometricFactory.getObject().removeDocuments(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/{id}/documents", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Object> updateDocuments(@RequestHeader(name = "biometricBureauName", required = true) String biometricBureauName,
                                                  @PathVariable("id") String idAnalyzeRequest,
                                                  @Valid @RequestBody List<DocumentDTO> documents) throws ServiceException {
        biometricFactory.getObject().uploadDocuments(idAnalyzeRequest, documents);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}