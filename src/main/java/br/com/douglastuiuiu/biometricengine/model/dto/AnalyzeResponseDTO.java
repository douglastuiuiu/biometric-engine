package br.com.douglastuiuiu.biometricengine.model.dto;

import br.com.douglastuiuiu.biometricengine.model.enumeration.AnalyzeStatusEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

/**
 * @author douglas
 * @since 21/03/2017
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class AnalyzeResponseDTO implements Serializable {

    private static final long serialVersionUID = -4597212872542946562L;

    @NotEmpty
    private String id;
    private String bureauId;
    @NotNull
    private AnalyzeStatusEnum status;
    private String description;

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

    public AnalyzeStatusEnum getStatus() {
        return status;
    }

    public void setStatus(AnalyzeStatusEnum status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Analyze [id=" + id + ", status=" + status + "]";
    }
}
