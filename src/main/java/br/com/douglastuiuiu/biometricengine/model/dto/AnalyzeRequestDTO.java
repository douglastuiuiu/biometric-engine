package br.com.douglastuiuiu.biometricengine.model.dto;

import br.com.douglastuiuiu.biometricengine.model.builder.AnalyzeRequestDTOBuilder;
import br.com.douglastuiuiu.biometricengine.model.document.AnalyzeRequest;
import br.com.douglastuiuiu.biometricengine.model.enumeration.AnalyzeStatusEnum;
import br.com.douglastuiuiu.biometricengine.model.enumeration.ConsumerTypeEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

/**
 * @author douglas
 * @since 21/03/2017
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class AnalyzeRequestDTO implements Serializable {

    private static final long serialVersionUID = 3508619570502268364L;

    private String id;
    private String bureauId;
    @NotNull
    private ConsumerTypeEnum consumerType;
    @NotNull
    @Valid
    private PersonDTO person;
    @NotNull
    private String establishmentId;
    private AnalyzeStatusEnum status;

    public static AnalyzeRequestDTO parseAnalyzeRequestDTO(AnalyzeRequest analyzeRequest) {
        return AnalyzeRequestDTOBuilder.newInstance()
                .withConsumerType(analyzeRequest.getConsumerType())
                .withBureauId(analyzeRequest.getBureauId())
                .withEstablishmentId(analyzeRequest.getEstablishmentId())
                .withPerson(analyzeRequest.getPerson())
                .withStatus(analyzeRequest.getStatus())
                .build();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBureauId() {
        return bureauId;
    }

    public void setBureauId(String bureauId) {
        this.bureauId = bureauId;
    }

    public ConsumerTypeEnum getConsumerType() {
        return consumerType;
    }

    public void setConsumerType(ConsumerTypeEnum consumerType) {
        this.consumerType = consumerType;
    }

    public PersonDTO getPerson() {
        return person;
    }

    public void setPerson(PersonDTO person) {
        this.person = person;
    }

    public String getEstablishmentId() {
        return establishmentId;
    }

    public void setEstablishmentId(String establishmentId) {
        this.establishmentId = establishmentId;
    }

    public AnalyzeStatusEnum getStatus() {
        return status;
    }

    public void setStatus(AnalyzeStatusEnum status) {
        this.status = status;
    }
}
