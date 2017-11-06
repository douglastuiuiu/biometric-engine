package br.com.douglastuiuiu.biometricengine.model.builder;

import br.com.douglastuiuiu.biometricengine.model.document.Person;
import br.com.douglastuiuiu.biometricengine.model.dto.AnalyzeRequestDTO;
import br.com.douglastuiuiu.biometricengine.model.dto.PersonDTO;
import br.com.douglastuiuiu.biometricengine.model.enumeration.AnalyzeStatusEnum;
import br.com.douglastuiuiu.biometricengine.model.enumeration.ConsumerTypeEnum;

/**
 * @author douglas
 * @since 23/03/2017
 */
public class AnalyzeRequestDTOBuilder {

    private AnalyzeRequestDTO analyzeRequestDTO;

    private AnalyzeRequestDTOBuilder() {
        analyzeRequestDTO = new AnalyzeRequestDTO();
    }

    public static AnalyzeRequestDTOBuilder newInstance() {
        return new AnalyzeRequestDTOBuilder();
    }

    public AnalyzeRequestDTOBuilder withBureauId(String bureauId) {
        analyzeRequestDTO.setBureauId(bureauId);
        return this;
    }

    public AnalyzeRequestDTOBuilder withConsumerType(ConsumerTypeEnum consumerType) {
        analyzeRequestDTO.setConsumerType(consumerType);
        return this;
    }

    public AnalyzeRequestDTOBuilder withPerson(Person person) {
        analyzeRequestDTO.setPerson(PersonDTO.parsePersonDTO(person));
        return this;
    }

    public AnalyzeRequestDTOBuilder withEstablishmentId(String establishmentId) {
        analyzeRequestDTO.setEstablishmentId(establishmentId);
        return this;
    }

    public AnalyzeRequestDTOBuilder withStatus(AnalyzeStatusEnum status) {
        analyzeRequestDTO.setStatus(status);
        return this;
    }

    public AnalyzeRequestDTO build() {
        return analyzeRequestDTO;
    }

}
