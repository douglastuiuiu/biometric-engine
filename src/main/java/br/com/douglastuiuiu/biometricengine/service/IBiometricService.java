package br.com.douglastuiuiu.biometricengine.service;

import br.com.douglastuiuiu.biometricengine.exception.ServiceException;
import br.com.douglastuiuiu.biometricengine.model.dto.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * @author douglas
 * @since 21/03/2017
 */
public interface IBiometricService {

    ResponseEntity<AnalyzeResponseDTO> createAnalyzeRequest(AnalyzeRequestDTO analyzeRequestDTO) throws ServiceException;

    AnalyzeResponseDTO updatePhoto(String id, PhotoDTO photoDTO) throws ServiceException;

    ResponseEntity<Object> getAnalyze(String id) throws ServiceException;

    void setAnalyzeFraud(String id, CommentDTO commentDTO) throws ServiceException;

    void removeDocuments(String id) throws ServiceException;

    AnalyzeResponseDTO uploadDocuments(String id, List<DocumentDTO> documents) throws ServiceException;

}
