package br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.response.pooling;

import java.io.Serializable;
import java.util.List;

/**
 * @author douglas
 * @since 20/03/2017
 */
public class PoolingCreditDTO implements Serializable {

    private static final long serialVersionUID = -5519263110193215065L;

    private Integer id;
    private String fraud;
    private String state;
    private Integer state_id;
    private String biometry_result;
    private List<String> alert;
    private PoolingCol1DTO col1;
    private PoolingCol2DTO col2;
    private PoolingCol3DTO col3;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFraud() {
        return fraud;
    }

    public void setFraud(String fraud) {
        this.fraud = fraud;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getState_id() {
        return state_id;
    }

    public void setState_id(Integer state_id) {
        this.state_id = state_id;
    }

    public String getBiometry_result() {
        return biometry_result;
    }

    public void setBiometry_result(String biometry_result) {
        this.biometry_result = biometry_result;
    }

    public List<String> getAlert() {
        return alert;
    }

    public void setAlert(List<String> alert) {
        this.alert = alert;
    }

    public PoolingCol1DTO getCol1() {
        return col1;
    }

    public void setCol1(PoolingCol1DTO col1) {
        this.col1 = col1;
    }

    public PoolingCol2DTO getCol2() {
        return col2;
    }

    public void setCol2(PoolingCol2DTO col2) {
        this.col2 = col2;
    }

    public PoolingCol3DTO getCol3() {
        return col3;
    }

    public void setCol3(PoolingCol3DTO col3) {
        this.col3 = col3;
    }
}
