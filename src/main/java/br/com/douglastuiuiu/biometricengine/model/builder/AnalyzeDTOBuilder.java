package br.com.douglastuiuiu.biometricengine.model.builder;

import br.com.douglastuiuiu.biometricengine.exception.IntegrationException;
import br.com.douglastuiuiu.biometricengine.model.document.AnalyzeRequest;
import br.com.douglastuiuiu.biometricengine.model.document.Person;
import br.com.douglastuiuiu.biometricengine.model.dto.AnalyzeDTO;
import br.com.douglastuiuiu.biometricengine.model.dto.AnalyzeRequestDTO;
import br.com.douglastuiuiu.biometricengine.model.dto.PersonDTO;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author douglas
 * @since 24/03/2017
 */
public class AnalyzeDTOBuilder {

    private AnalyzeDTO analyzeDTO;

    private AnalyzeDTOBuilder() {
        analyzeDTO = new AnalyzeDTO();
    }

    public static AnalyzeDTOBuilder newInstance() {
        return new AnalyzeDTOBuilder();
    }

    public AnalyzeDTOBuilder withBureauStatus(String bureauStatus) {
        analyzeDTO.setBureauStatus(bureauStatus);
        return this;
    }

    public AnalyzeDTOBuilder withRequest(AnalyzeRequest request) {
        analyzeDTO.setRequest(AnalyzeRequestDTO.parseAnalyzeRequestDTO(request));
        return this;
    }

    public AnalyzeDTOBuilder withCustomer(Person customer) throws IntegrationException {
        analyzeDTO.setCustomer(PersonDTO.parsePersonDTO(customer));
        return this;
    }

    public AnalyzeDTOBuilder withCustomerSimilars(List<Person> customerSimilars) {
        analyzeDTO.setCustomerSimilars(customerSimilars != null ? customerSimilars.stream().filter(Objects::nonNull).map(PersonDTO::parsePersonDTO).collect(Collectors.toList()) : null);
        return this;
    }

    public AnalyzeDTOBuilder withRequestSimilars(List<Person> requestSimilars) {
        analyzeDTO.setRequestSimilars(requestSimilars != null ? requestSimilars.stream().filter(Objects::nonNull).map(PersonDTO::parsePersonDTO).collect(Collectors.toList()) : null);
        return this;
    }

    public AnalyzeDTO build() {
        return analyzeDTO;
    }

}
