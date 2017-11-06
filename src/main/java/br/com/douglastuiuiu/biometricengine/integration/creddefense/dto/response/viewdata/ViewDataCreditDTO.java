package br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.response.viewdata;

import java.io.Serializable;
import java.util.List;

/**
 * @author douglas
 * @since 20/03/2017
 */
public class ViewDataCreditDTO implements Serializable {

    private static final long serialVersionUID = 3370690558001530467L;

    private Integer id;
    private String fraud;
    private String state;
    private Integer state_id;
    private String biometry_result;
    private List<String> alert;
    private ViewDataCol1DTO col1;
    private ViewDataCol2DTO col2;
    private ViewDataCol3DTO col3;

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

    public ViewDataCol1DTO getCol1() {
        return col1;
    }

    public void setCol1(ViewDataCol1DTO col1) {
        this.col1 = col1;
    }

    public ViewDataCol2DTO getCol2() {
        return col2;
    }

    public void setCol2(ViewDataCol2DTO col2) {
        this.col2 = col2;
    }

    public ViewDataCol3DTO getCol3() {
        return col3;
    }

    public void setCol3(ViewDataCol3DTO col3) {
        this.col3 = col3;
    }
}
