package br.com.douglastuiuiu.biometricengine.model.dto;

import br.com.douglastuiuiu.biometricengine.model.document.Person;
import br.com.douglastuiuiu.biometricengine.serializer.LocalDateDeserializer;
import br.com.douglastuiuiu.biometricengine.serializer.LocalDateSerializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

/**
 * @author douglas
 * @since 21/03/2017
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class PersonDTO implements Serializable {

    private static final long serialVersionUID = -8142870329005943509L;

    @Pattern(regexp = "[0-9]{3}\\.?[0-9]{3}\\.?[0-9]{3}\\-?[0-9]{2}")
    @NotEmpty
    private String cpf;
    @NotEmpty
    private String name;
    @NotEmpty
    private String gender;
    @NotNull
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate birthdate;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate bureauRegisterDate;
    @Valid
    private PhotoDTO photo;
    @Valid
    private List<DocumentDTO> documents;
    @NotNull
    private Boolean isFraud;

    public static PersonDTO parsePersonDTO(Person person) {
        if (person == null) {
            return null;
        }
        PersonDTO personDTO = new PersonDTO();
        personDTO.setName(person.getName());
        personDTO.setGender(person.getGender());
        personDTO.setIsFraud(person.getIsFraud());
        personDTO.setCpf(person.getCpf());
        personDTO.setBirthdate(person.getBirthdate());
        personDTO.setBureauRegisterDate(person.getBureauRegisterDate());
        personDTO.setPhoto(person.getPhoto() == null ? null : new PhotoDTO()); // TODO: 4/11/17 carregar imagens do minio
        personDTO.setDocuments(person.getDocuments() != null ? person.getDocuments().stream().filter(Objects::nonNull).map(DocumentDTO::parseDocumentDTO).collect(Collectors.toList()) : null);
        return personDTO;
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

    public PhotoDTO getPhoto() {
        return photo;
    }

    public void setPhoto(PhotoDTO photo) {
        this.photo = photo;
    }

    public List<DocumentDTO> getDocuments() {
        return documents != null ? documents : new ArrayList<>();
    }

    public void setDocuments(List<DocumentDTO> documents) {
        this.documents = documents;
    }

    public Boolean getIsFraud() {
        return isFraud;
    }

    public void setIsFraud(Boolean isFraud) {
        this.isFraud = isFraud;
    }

}
