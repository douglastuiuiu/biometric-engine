package br.com.douglastuiuiu.biometricengine.config;

import io.minio.MinioClient;
import io.minio.errors.InvalidEndpointException;
import io.minio.errors.InvalidPortException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:minio/path.properties")
public class MinioConfig {

    @Value("${minio.storage.host}")
    private String host;
    @Value("${minio.storage.accesskey}")
    private String accesskey;
    @Value("${minio.storage.bucket}")
    private String bucket;
    @Value("${minio.storage.secretkey}")
    private String secretkey;

    @Bean
    public MinioClient minioClient() throws InvalidEndpointException, InvalidPortException {
        return new MinioClient(host, accesskey, secretkey);
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getAccesskey() {
        return accesskey;
    }

    public void setAccesskey(String accesskey) {
        this.accesskey = accesskey;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getSecretkey() {
        return secretkey;
    }

    public void setSecretkey(String secretkey) {
        this.secretkey = secretkey;
    }
}