package br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.response.viewdata;

import java.io.Serializable;
import java.util.List;

/**
 * @author douglas
 * @since 31/03/2017
 */
public class ViewDataCol3DTO implements Serializable {

    private static final long serialVersionUID = 273431933760233099L;

    private List<ViewDataCol3CreditRequestSimilarsDTO> creditrequest_similars;
    private List<ViewDataCol3CustomerSimilarsDTO> customer_similars;

    public List<ViewDataCol3CreditRequestSimilarsDTO> getCreditrequest_similars() {
        return creditrequest_similars;
    }

    public void setCreditrequest_similars(List<ViewDataCol3CreditRequestSimilarsDTO> creditrequest_similars) {
        this.creditrequest_similars = creditrequest_similars;
    }

    public List<ViewDataCol3CustomerSimilarsDTO> getCustomer_similars() {
        return customer_similars;
    }

    public void setCustomer_similars(List<ViewDataCol3CustomerSimilarsDTO> customer_similars) {
        this.customer_similars = customer_similars;
    }
}
