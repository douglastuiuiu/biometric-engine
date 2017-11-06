package br.com.douglastuiuiu.biometricengine.service;

import br.com.douglastuiuiu.biometricengine.exception.IntegrationException;
import br.com.douglastuiuiu.biometricengine.exception.ServiceException;
import br.com.douglastuiuiu.biometricengine.integration.minio.integration.MinioIntegration;
import br.com.douglastuiuiu.biometricengine.model.document.Analyze;
import br.com.douglastuiuiu.biometricengine.model.document.AnalyzeRequest;
import br.com.douglastuiuiu.biometricengine.model.enumeration.AnalyzeStatusEnum;
import br.com.douglastuiuiu.biometricengine.model.enumeration.StorageStatusEnum;
import br.com.douglastuiuiu.biometricengine.persistence.AnalyzePersistence;
import br.com.douglastuiuiu.biometricengine.persistence.AnalyzeRequestPersistence;
import br.com.douglastuiuiu.biometricengine.util.MessageLocale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

/**
 * @author douglas
 * @since 06/04/2017
 */
@Service
public class StorageService {

    private static final Logger logger = LoggerFactory.getLogger(StorageService.class);
    private static final List<String> LOGO_MIME_TYPES = Arrays.asList("image/jpg", "image/jpeg", "image/png", "image/bmp");
    @Autowired
    private MinioIntegration minioIntegration;
    @Autowired
    private AnalyzeRequestPersistence analyzeRequestPersistence;
    @Autowired
    private AnalyzePersistence analyzePersistence;
    @Autowired
    private MessageLocale messageLocale;
    @Value("${minio.storage.max_file_size}")
    private Integer FILE_MAX_SIZE;

    public void purgeAnalyzeExpiredFiles() throws ServiceException {
        try {
            List<Analyze> expiredAnalysis = analyzePersistence.findExpiredAnalysis();
            for (Analyze analyze : expiredAnalysis) {
                if (analyze.getRequest().getId() != null) {
                    AnalyzeRequest analyzeRequest = analyzeRequestPersistence.findById(analyze.getRequest().getId());
                    analyzeRequest.setStatus(AnalyzeStatusEnum.DONE);
                    analyzeRequestPersistence.updateAndFind(analyzeRequest);
                }

                if (analyze != null) {
                    analyze.setStorageStatus(StorageStatusEnum.PURGED);
                    analyzePersistence.updateAndFind(analyze);
                }
            }
            minioIntegration.purgeExpiredFiles();
        } catch (IntegrationException e) {
            logger.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public void validateImagemSize(String base64) throws ServiceException {
        base64 = base64.substring(base64.indexOf(",") + 1);
        byte[] bytes = Base64.getDecoder().decode(base64);
        InputStream stream = new ByteArrayInputStream(bytes);

        if (bytes != null && bytes.length > FILE_MAX_SIZE) {
            throw new ServiceException(messageLocale.getMessage("error.analyze_invalid_image_size"));
        }
    }

    public void validateImagemMimeType(String base64) throws ServiceException {
        try {
            base64 = base64.substring(base64.indexOf(",") + 1);
            byte[] bytes = Base64.getDecoder().decode(base64);
            InputStream stream = new ByteArrayInputStream(bytes);
            String mimeType = URLConnection.guessContentTypeFromStream(stream);

            if (!LOGO_MIME_TYPES.contains(mimeType)) {
                throw new ServiceException(messageLocale.getMessage("error.analyze_invalid_image_type"));
            }
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
