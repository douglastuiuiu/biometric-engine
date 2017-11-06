package br.com.douglastuiuiu.biometricengine.model.document;

import br.com.douglastuiuiu.biometricengine.model.dto.PersonDTO;
import br.com.douglastuiuiu.biometricengine.model.enumeration.creddefense.PhotoSufixEnum;
import br.com.douglastuiuiu.biometricengine.serializer.LocalDateDeserializer;
import br.com.douglastuiuiu.biometricengine.serializer.LocalDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author douglas
 * @since 23/03/2017
 */
@org.springframework.data.mongodb.core.mapping.Document(collection = "person")
public class Person implements Serializable {

    private static final long serialVersionUID = 1417534610505081448L;

    @Id
    private String id;
    @Field
    private String cpf;
    @Field
    private String name;
    @Field
    private String gender;
    @Field
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate birthdate;
    @Field
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate bureauRegisterDate;
    @Field
    private Photo photo;
    @Field
    private List<Document> documents;
    @Field
    private Boolean isFraud;

    public Person() {

    }

    public static Person parseToPerson(PersonDTO personDTO) {
        Person person = new Person();
        person.setName(personDTO.getName());
        person.setGender(personDTO.getGender());
        person.setIsFraud(personDTO.getIsFraud());
        person.setCpf(personDTO.getCpf());
        person.setBirthdate(personDTO.getBirthdate());
        person.setPhoto(personDTO.getPhoto() != null ? new Photo(personDTO.getCpf().concat(PhotoSufixEnum.PHOTO.getName())) : null);
        person.setDocuments(personDTO.getDocuments() != null ? personDTO.getDocuments().stream().filter(Objects::nonNull).map(Document::parseDocument).collect(Collectors.toList()) : null);
        return person;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public LocalDate getBureauRegisterDate() {
        return bureauRegisterDate;
    }

    public void setBureauRegisterDate(LocalDate bureauRegisterDate) {
        this.bureauRegisterDate = bureauRegisterDate;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photoName) {
        this.photo = photoName;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    public Boolean getIsFraud() {
        return isFraud;
    }

    public void setIsFraud(Boolean isFraud) {
        this.isFraud = isFraud;
    }

}
