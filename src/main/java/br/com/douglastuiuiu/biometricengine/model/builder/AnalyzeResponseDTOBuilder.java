package br.com.douglastuiuiu.biometricengine.model.builder;

import br.com.douglastuiuiu.biometricengine.model.dto.AnalyzeResponseDTO;
import br.com.douglastuiuiu.biometricengine.model.enumeration.AnalyzeStatusEnum;

/**
 * @author douglas
 * @since 05/04/2017
 */
public class AnalyzeResponseDTOBuilder {

    private AnalyzeResponseDTO analyzeResponseDTO;

    private AnalyzeResponseDTOBuilder() {
        analyzeResponseDTO = new AnalyzeResponseDTO();
    }

    public static AnalyzeResponseDTOBuilder newInstance() {
        return new AnalyzeResponseDTOBuilder();
    }

    public AnalyzeResponseDTOBuilder withId(String id) {
        analyzeResponseDTO.setId(id);
        return this;
    }

    public AnalyzeResponseDTOBuilder withBureauId(String bureauId) {
        analyzeResponseDTO.setBureauId(bureauId);
        return this;
    }

    public AnalyzeResponseDTOBuilder withStatus(AnalyzeStatusEnum status) {
        analyzeResponseDTO.setStatus(status);
        return this;
    }

    public AnalyzeResponseDTOBuilder withDescription(String description) {
        analyzeResponseDTO.setDescription(description);
        return this;
    }

    public AnalyzeResponseDTO build() {
        return analyzeResponseDTO;
    }

}
