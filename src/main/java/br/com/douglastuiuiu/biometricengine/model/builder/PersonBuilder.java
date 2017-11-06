package br.com.douglastuiuiu.biometricengine.model.builder;

import br.com.douglastuiuiu.biometricengine.model.document.Document;
import br.com.douglastuiuiu.biometricengine.model.document.Person;
import br.com.douglastuiuiu.biometricengine.model.document.Photo;
import br.com.douglastuiuiu.biometricengine.util.Normalizer;

import java.time.LocalDate;
import java.util.List;

/**
 * @author douglas
 * @since 24/03/2017
 */
public class PersonBuilder {

    private Person person;

    private PersonBuilder() {
        person = new Person();
    }

    public static PersonBuilder newInstance() {
        return new PersonBuilder();
    }

    public PersonBuilder withCpf(String cpf) {
        person.setCpf(Normalizer.normalizeText(cpf));
        return this;
    }

    public PersonBuilder withName(String name) {
        person.setName(name);
        return this;
    }

    public PersonBuilder withBirthdate(LocalDate birthdate) {
        person.setBirthdate(birthdate);
        return this;
    }

    public PersonBuilder withBureauRegisterDate(LocalDate bureauRegisterDate) {
        person.setBureauRegisterDate(bureauRegisterDate);
        return this;
    }

    public PersonBuilder withPhoto(String id, String base64, String photoSufixEnum) {
        if (base64 != null) {
            if (id == null) {
                throw new RuntimeException("[PersonBuilder] Falha ao montar objeto Photo. ID null.");
            }
            person.setPhoto(new Photo(id.concat(photoSufixEnum)));
        }
        return this;
    }

    public PersonBuilder withDocuments(List<Document> documents) {
        person.setDocuments(documents);
        return this;
    }

    public PersonBuilder withIsFraud(Boolean isFraud) {
        person.setIsFraud(isFraud);
        return this;
    }

    public Person build() {
        return person;
    }

}
