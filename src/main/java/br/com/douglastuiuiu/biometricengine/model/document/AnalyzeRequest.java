package br.com.douglastuiuiu.biometricengine.model.document;

import br.com.douglastuiuiu.biometricengine.model.builder.AnalyzeRequestBuilder;
import br.com.douglastuiuiu.biometricengine.model.dto.AnalyzeRequestDTO;
import br.com.douglastuiuiu.biometricengine.model.enumeration.AnalyzeStatusEnum;
import br.com.douglastuiuiu.biometricengine.model.enumeration.ConsumerTypeEnum;
import br.com.douglastuiuiu.biometricengine.serializer.LocalDateTimeDeserializer;
import br.com.douglastuiuiu.biometricengine.serializer.LocalDateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author douglas
 * @since 23/03/2017
 */
@Document(collection = "analyzeRequest")
@CompoundIndexes({@CompoundIndex(name = "index_analyzeRequest", def = "{'consumerType' : 1, 'status' : 1, 'person.cpf': 1}")})
public class AnalyzeRequest implements Serializable {

    private static final long serialVersionUID = -1464650808027731184L;

    @Id
    private String id;
    @Field
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime registerDate;
    @Field
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime lastUpdate;

    @Field
    private String bureauId;
    @Field
    private ConsumerTypeEnum consumerType;
    @Field
    private Person person;
    @Field
    private String establishmentId;
    @Field
    private AnalyzeStatusEnum status;

    public AnalyzeRequest() {

    }

    public static AnalyzeRequest parseAnalyzeRequest(AnalyzeRequestDTO analyzeRequestDTO) {
        return AnalyzeRequestBuilder.newInstance()
                .withId(analyzeRequestDTO.getId())
                .withBureauId(analyzeRequestDTO.getBureauId())
                .withConsumerType(analyzeRequestDTO.getConsumerType())
                .withEstablishmentId(analyzeRequestDTO.getEstablishmentId())
                .withPerson(analyzeRequestDTO.getPerson())
                .withStatus(analyzeRequestDTO.getStatus())
                .build();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(LocalDateTime registerDate) {
        this.registerDate = registerDate;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
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

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
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
