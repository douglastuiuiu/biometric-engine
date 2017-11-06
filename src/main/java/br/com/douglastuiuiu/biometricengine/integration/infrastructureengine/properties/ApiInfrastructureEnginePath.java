package br.com.douglastuiuiu.biometricengine.integration.infrastructureengine.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author douglasg
 * @since 10/03/2017
 */
@Configuration
@PropertySource("classpath:infrastructureengine/path.properties")
public class ApiInfrastructureEnginePath {

    private static final String HTTP = "http://";
    private static final String PORT_PREFIX = ":";

    @Value("${api.path.infrastructureengine.host}")
    private String host;

    @Value("${api.path.infrastructureengine.port}")
    private String port;

    @Value("${api.path.infrastructureengine.basePath}")
    private String basePath;

    @Value("${api.path.infrastructureengine.dateTime}")
    private String dateTime;

    public String getHost() {
        return host + PORT_PREFIX + getPort();
    }

    public String getBasePath() {
        return basePath;
    }

    public String getDateTime() {
        return getEndpoint() + dateTime;
    }

    private String getEndpoint() {
        return HTTP + getHost() + basePath;
    }

    public String getPort() {
        return port;
    }
}
