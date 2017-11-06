package br.com.douglastuiuiu.biometricengine.integration.minio.integration;

import br.com.douglastuiuiu.biometricengine.config.MinioConfig;
import br.com.douglastuiuiu.biometricengine.exception.IntegrationException;
import br.com.douglastuiuiu.biometricengine.model.dto.DocumentDTO;
import br.com.douglastuiuiu.biometricengine.util.MessageLocale;
import com.google.common.io.BaseEncoding;
import com.google.common.io.ByteStreams;
import io.minio.MinioClient;
import io.minio.Result;
import io.minio.errors.*;
import io.minio.messages.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.xmlpull.v1.XmlPullParserException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Base64;
import java.util.List;

/**
 * @author douglasg
 * @since 24/03/2017
 */
@Component
public class MinioIntegration {

    private final String jpgExtension = ".JPG";
    private final String pngExtension = ".PNG";
    private final String bmpExtension = ".BMP";
    @Value("${batch.scheduler.storagepurge.hours}")
    String hoursToPurge;
    @Autowired
    private MessageLocale messageLocale;
    @Autowired
    private MinioConfig minioConfig;
    @Autowired
    private MinioClient minioClient;

    public void uploadFile(String filename, String base64) throws IntegrationException {
        try {
            validateBucket();
            base64 = base64.substring(base64.indexOf(",") + 1);
            byte[] bytes = Base64.getDecoder().decode(base64);
            InputStream stream = new ByteArrayInputStream(bytes);
            String mimeType = URLConnection.guessContentTypeFromStream(stream);
            filename = filename.concat(getFileExtension(mimeType));
            minioClient.putObject(minioConfig.getBucket(), filename, stream, bytes.length, mimeType);
        } catch (Exception e) {
            throw new IntegrationException(e.getMessage(), e);
        }
    }

    public void uploadDocuments(List<DocumentDTO> documents) throws IntegrationException {
        try {
            for (DocumentDTO documentDTO : documents) {
                uploadFile(documentDTO.getName(), documentDTO.getBase64());
            }
        } catch (Exception e) {
            throw new IntegrationException(e.getMessage(), e);
        }
    }

    public String getFile(String prefix) throws IntegrationException {
        try {
            String bucketName = minioConfig.getBucket();
            Iterable<Result<Item>> myObjects = minioClient.listObjects(bucketName, prefix);
            for (Result<Item> result : myObjects) {
                Item item = result.get();
                minioClient.statObject(bucketName, item.objectName());
                InputStream inputStream = minioClient.getObject(bucketName, item.objectName());
                return encodeToBase64(inputStream);
            }
            return null;
        } catch (ErrorResponseException e) {
            throw new IntegrationException(messageLocale.getMessage("error.storage.search_error"), e);
        } catch (Exception e) {
            throw new IntegrationException(messageLocale.getMessage("error.storage.search_error"), e);
        }
    }

//    public String getFiles(String prefix) throws StorageServiceException {
//        try {
//            String bucketName = minioConfig.getBucket();
//            Iterable<Result<Item>> myObjects = minioClient.listObjects(bucketName, prefix);
//            for (Result<Item> result : myObjects) {
//                Item item = result.get();
//                System.out.println(item.lastModified() + ", " + item.size() + ", " + item.objectName());
//            }
//            return "";
//        } catch (Exception e) {
//            throw new StorageServiceException(messageLocale.getMessage("error.storage.search_error"), e);
//        }
//    }

    private void validateBucket() throws InvalidBucketNameException, NoSuchAlgorithmException, InsufficientDataException, IOException, InvalidKeyException, NoResponseException, XmlPullParserException, ErrorResponseException, InternalException, IntegrationException {
        if (!minioClient.bucketExists(minioConfig.getBucket())) {
            createBucket();
        }
    }

    private void createBucket() throws IntegrationException {
        try {
            minioClient.makeBucket(minioConfig.getBucket());
        } catch (Exception e) {
            throw new IntegrationException(e.getMessage(), e);
        }
    }

    private String encodeToBase64(InputStream inputStream) throws IntegrationException {
        try {
            return ("data:image/jpeg;base64,".concat(BaseEncoding.base64().encode(ByteStreams.toByteArray(inputStream))));
        } catch (IOException e) {
            throw new IntegrationException(messageLocale.getMessage("error.storage.encoding_error"), e);
        }
    }

    public void purgeExpiredFiles() throws IntegrationException {
        try {
            String bucketName = minioConfig.getBucket();
            if (minioClient.bucketExists(bucketName)) {
                Iterable<Result<Item>> myObjects = minioClient.listObjects(bucketName);
                for (Result<Item> result : myObjects) {
                    Item item = result.get();
                    if (item != null && item.lastModified() != null) {
                        Instant instant = item.lastModified().toInstant();
                        ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
                        LocalDateTime itemDate = zdt.toLocalDateTime();
                        LocalDateTime currentDate = LocalDateTime.now().minusHours(Integer.parseInt(hoursToPurge));

                        if (itemDate.isBefore(currentDate)) {
                            minioClient.removeObject(bucketName, item.objectName());
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new IntegrationException(e.getMessage(), e);
        }
    }

    public void removeFiles(String prefix) throws IntegrationException {
        try {
            String bucketName = minioConfig.getBucket();
            if (minioClient.bucketExists(bucketName)) {
                Iterable<Result<Item>> myObjects = minioClient.listObjects(bucketName, prefix);
                for (Result<Item> result : myObjects) {
                    Item item = result.get();
                    minioClient.removeObject(bucketName, item.objectName());
                }
            }
        } catch (Exception e) {
            throw new IntegrationException(e.getMessage(), e);
        }
    }

    public void removeBucket() throws IntegrationException {
        try {
            String bucketName = minioConfig.getBucket();
            if (minioClient.bucketExists(bucketName)) {
                Iterable<Result<Item>> myObjects = minioClient.listObjects(bucketName);
                for (Result<Item> result : myObjects) {
                    Item item = result.get();
                    minioClient.removeObject(bucketName, item.objectName());
                }

                minioClient.removeBucket(bucketName);
            }
        } catch (Exception e) {
            throw new IntegrationException(e.getMessage(), e);
        }
    }

    private String getFileExtension(String mimeType) {
        if (mimeType.indexOf("jpeg") >= 0) {
            return jpgExtension;
        } else if (mimeType.indexOf("png") >= 0) {
            return pngExtension;
        } else if (mimeType.indexOf("bmp") >= 0) {
            return bmpExtension;
        }
        return "";
    }

}
