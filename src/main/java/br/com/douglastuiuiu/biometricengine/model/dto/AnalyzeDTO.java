package br.com.douglastuiuiu.biometricengine.model.dto;

import br.com.douglastuiuiu.biometricengine.exception.IntegrationException;
import br.com.douglastuiuiu.biometricengine.model.builder.AnalyzeDTOBuilder;
import br.com.douglastuiuiu.biometricengine.model.document.Analyze;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

/**
 * @author douglas
 * @since 21/03/2017
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class AnalyzeDTO implements Serializable {

    private static final long serialVersionUID = 4258368167165669453L;

    @NotEmpty
    private String bureauStatus;
    @NotNull
    @Valid
    private AnalyzeRequestDTO request;
    @Valid
    private PersonDTO customer;
    @Valid
    private List<PersonDTO> customerSimilars = new ArrayList<>();
    @Valid
    private List<PersonDTO> requestSimilars = new ArrayList<>();

    public static AnalyzeDTO parseAnalyzeDTO(Analyze analyze) throws IntegrationException {
        return AnalyzeDTOBuilder.newInstance()
                .withBureauStatus(analyze.getBureauStatus())
                .withRequest(analyze.getRequest())
                .withCustomer(analyze.getCustomer())
                .withCustomerSimilars(analyze.getCustomerSimilars())
                .withRequestSimilars(analyze.getRequestSimilars())
                .build();
    }

    public String getBureauStatus() {
        return bureauStatus;
    }

    public void setBureauStatus(String bureauStatus) {
        this.bureauStatus = bureauStatus;
    }

    public AnalyzeRequestDTO getRequest() {
        return request;
    }

    public void setRequest(AnalyzeRequestDTO request) {
        this.request = request;
    }

    public PersonDTO getCustomer() {
        return customer;
    }

    public void setCustomer(PersonDTO customer) {
        this.customer = customer;
    }

    public List<PersonDTO> getCustomerSimilars() {
        return customerSimilars;
    }

    public void setCustomerSimilars(List<PersonDTO> customerSimilars) {
        this.customerSimilars = customerSimilars;
    }

    public List<PersonDTO> getRequestSimilars() {
        return requestSimilars;
    }

    public void setRequestSimilars(List<PersonDTO> requestSimilars) {
        this.requestSimilars = requestSimilars;
    }

}
