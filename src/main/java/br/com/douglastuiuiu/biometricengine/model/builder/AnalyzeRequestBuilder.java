package br.com.douglastuiuiu.biometricengine.model.builder;

import br.com.douglastuiuiu.biometricengine.model.document.AnalyzeRequest;
import br.com.douglastuiuiu.biometricengine.model.document.Person;
import br.com.douglastuiuiu.biometricengine.model.dto.PersonDTO;
import br.com.douglastuiuiu.biometricengine.model.enumeration.AnalyzeStatusEnum;
import br.com.douglastuiuiu.biometricengine.model.enumeration.ConsumerTypeEnum;

import java.time.LocalDateTime;

/**
 * @author douglas
 * @since 23/03/2017
 */
public class AnalyzeRequestBuilder {

    private AnalyzeRequest analyzeRequest;

    private AnalyzeRequestBuilder() {
        analyzeRequest = new AnalyzeRequest();
    }

    public static AnalyzeRequestBuilder newInstance() {
        return new AnalyzeRequestBuilder();
    }

    public AnalyzeRequestBuilder withId(String id) {
        analyzeRequest.setId(id);
        return this;
    }

    public AnalyzeRequestBuilder withRegisterDate(LocalDateTime date) {
        analyzeRequest.setRegisterDate(date);
        return this;
    }

    public AnalyzeRequestBuilder withRefressDate(LocalDateTime date) {
        analyzeRequest.setLastUpdate(date);
        return this;
    }

    public AnalyzeRequestBuilder withBureauId(String bureauId) {
        analyzeRequest.setBureauId(bureauId);
        return this;
    }

    public AnalyzeRequestBuilder withConsumerType(ConsumerTypeEnum consumerType) {
        analyzeRequest.setConsumerType(consumerType);
        return this;
    }

    public AnalyzeRequestBuilder withPerson(PersonDTO personDTO) {
        analyzeRequest.setPerson(Person.parseToPerson(personDTO));
        return this;
    }

    public AnalyzeRequestBuilder withPerson(Person person) {
        analyzeRequest.setPerson(person);
        return this;
    }

    public AnalyzeRequestBuilder withEstablishmentId(String establishmentId) {
        analyzeRequest.setEstablishmentId(establishmentId);
        return this;
    }

    public AnalyzeRequestBuilder withStatus(AnalyzeStatusEnum status) {
        analyzeRequest.setStatus(status);
        return this;
    }

    public AnalyzeRequest build() {
        return analyzeRequest;
    }


}
