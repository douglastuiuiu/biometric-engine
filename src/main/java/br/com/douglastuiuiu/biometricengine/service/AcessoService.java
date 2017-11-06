package br.com.douglastuiuiu.biometricengine.service;

import br.com.douglastuiuiu.biometricengine.exception.ServiceException;
import br.com.douglastuiuiu.biometricengine.model.document.Analyze;
import br.com.douglastuiuiu.biometricengine.model.dto.*;
import br.com.douglastuiuiu.biometricengine.queue.dto.AnalyzeRequestMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

/**
 * @author douglas
 * @since 21/03/2017
 */
@Service
public class AcessoService implements IBiometricService {

    @Override
    public ResponseEntity<AnalyzeResponseDTO> createAnalyzeRequest(AnalyzeRequestDTO analyzeRequestDTO) throws ServiceException {
        throw new NotImplementedException();
    }

    @Override
    public AnalyzeResponseDTO updatePhoto(String id, PhotoDTO photoDTO) throws ServiceException {
        throw new NotImplementedException();
    }

    public void checkAnalyzeStatus(AnalyzeRequestMessage analyzeRequestMessage) throws ServiceException {
        throw new NotImplementedException();
    }

    private void createAnalyze(Analyze analyze) throws ServiceException {
        throw new NotImplementedException();
    }

    @Override
    public ResponseEntity<Object> getAnalyze(String id) throws ServiceException {
        throw new NotImplementedException();
    }

    @Override
    public void setAnalyzeFraud(String id, CommentDTO commentDTO) throws ServiceException {
        throw new NotImplementedException();
    }

    @Override
    public void removeDocuments(String id) throws ServiceException {
        throw new NotImplementedException();
    }

    @Override
    public AnalyzeResponseDTO uploadDocuments(String id, List<DocumentDTO> documents) throws ServiceException {
        throw new NotImplementedException();
    }

}