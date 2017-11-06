package br.com.douglastuiuiu.biometricengine.model.builder;

import br.com.douglastuiuiu.biometricengine.model.document.Analyze;
import br.com.douglastuiuiu.biometricengine.model.document.AnalyzeRequest;
import br.com.douglastuiuiu.biometricengine.model.document.Person;
import br.com.douglastuiuiu.biometricengine.model.dto.PersonDTO;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author douglas
 * @since 24/03/2017
 */
public class AnalyzeBuilder {

    private Analyze analyze;

    private AnalyzeBuilder() {
        analyze = new Analyze();
    }

    public static AnalyzeBuilder newInstance() {
        return new AnalyzeBuilder();
    }

    public AnalyzeBuilder withId(String id) {
        analyze.setId(id);
        return this;
    }

    public AnalyzeBuilder withRegisterDate(LocalDateTime date) {
        analyze.setRegisterDate(date);
        return this;
    }

    public AnalyzeBuilder withRefressDate(LocalDateTime date) {
        analyze.setLastUpdate(date);
        return this;
    }

    public AnalyzeBuilder withBureauStatus(String bureauStatus) {
        analyze.setBureauStatus(bureauStatus);
        return this;
    }

    public AnalyzeBuilder withRequest(AnalyzeRequest analyzeRequest) {
        analyze.setRequest(analyzeRequest);
        return this;
    }

    public AnalyzeBuilder withCustomer(PersonDTO personDTO) {
        analyze.setCustomer(Person.parseToPerson(personDTO));
        return this;
    }

    public AnalyzeBuilder withCustomer(Person person) {
        analyze.setCustomer(person);
        return this;
    }

    public AnalyzeBuilder withFraud(List<Person> fraud) {
        analyze.setFraud(fraud);
        return this;
    }

    public AnalyzeBuilder withCustomerSimilars(List<Person> similarPeople) {
        analyze.setCustomerSimilars(similarPeople);
        return this;
    }

    public AnalyzeBuilder withRequestSimilars(List<Person> similarPeople) {
        analyze.setRequestSimilars(similarPeople);
        return this;
    }

    public Analyze build() {
        return analyze;
    }

}
