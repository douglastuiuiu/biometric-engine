package br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.request.entity.viewdata;

import java.io.Serializable;

/**
 * @author douglas
 * @since 20/03/2017
 */
public class ViewDataCreditDTO implements Serializable {

    private static final long serialVersionUID = -2091824505232186627L;

    private Integer id;

    public ViewDataCreditDTO() {
    }

    public ViewDataCreditDTO(Integer id) {
        this.setId(id);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
