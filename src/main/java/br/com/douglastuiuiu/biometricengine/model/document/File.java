package br.com.douglastuiuiu.biometricengine.model.document;

import br.com.douglastuiuiu.biometricengine.model.enumeration.StorageStatusEnum;
import br.com.douglastuiuiu.biometricengine.serializer.LocalDateTimeDeserializer;
import br.com.douglastuiuiu.biometricengine.serializer.LocalDateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author douglas
 * @since 23/03/2017
 */
public abstract class File implements Serializable {

    private static final long serialVersionUID = 2256236957730009133L;

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
    private String name;
    @Field
    private StorageStatusEnum status;

    public File() {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StorageStatusEnum getStatus() {
        return status;
    }

    public void setStatus(StorageStatusEnum status) {
        this.status = status;
    }

}
