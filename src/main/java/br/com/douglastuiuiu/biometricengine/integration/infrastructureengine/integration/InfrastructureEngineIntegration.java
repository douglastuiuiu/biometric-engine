package br.com.douglastuiuiu.biometricengine.integration.infrastructureengine.integration;

import br.com.douglastuiuiu.biometricengine.integration.infrastructureengine.properties.ApiInfrastructureEnginePath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author douglasg
 * @since 10/03/2017
 */
@Component
public class InfrastructureEngineIntegration {

    @Autowired
    private ApiInfrastructureEnginePath apiInfrastructureEnginePath;

    public LocalDateTime getLocalDateTime() {
        String uri = apiInfrastructureEnginePath.getDateTime();
        Long longDateTime = new RestTemplate().getForEntity(uri, Long.class).getBody();
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(longDateTime), ZoneId.of("America/Sao_Paulo"));
    }

    public LocalDate getLocalDate() {
        return getLocalDateTime().toLocalDate();
    }

    public Date getDate() {
        Date date = Date.from(getLocalDateTime().atZone(ZoneId.of("America/Sao_Paulo")).toInstant());
        if (date == null) date = new Date();
        return date;
    }

}
