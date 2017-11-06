package br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.request.entity.credit;

import java.io.Serializable;
import java.util.List;

/**
 * @author douglas
 * @since 20/03/2017
 */
public class CreditRequestDTO implements Serializable {

    private static final long serialVersionUID = 870598699645914712L;

    private List<CreditDTO> creditrequest;
    private CreditAuthenticationDTO authentication;

    public CreditRequestDTO() {
    }

    public CreditRequestDTO(CreditAuthenticationDTO authentication) {
        super();
        this.authentication = authentication;
    }

    public List<CreditDTO> getCreditrequest() {
        return creditrequest;
    }

    public void setCreditrequest(List<CreditDTO> creditrequest) {
        this.creditrequest = creditrequest;
    }

    public CreditAuthenticationDTO getAuthentication() {
        return authentication;
    }

    public void setAuthentication(CreditAuthenticationDTO authentication) {
        this.authentication = authentication;
    }

}
