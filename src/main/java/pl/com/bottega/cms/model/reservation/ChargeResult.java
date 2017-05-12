package pl.com.bottega.cms.model.reservation;

import javax.persistence.Embeddable;

@Embeddable
public class ChargeResult {

    private String status;
    private String number;
    private String errorMessage;

    public ChargeResult() {
    }

    public ChargeResult(String status, String number, String errorMessage) {
        this.status = status;
        this.number = number;
        this.errorMessage = errorMessage;
    }

    public String getStatus() {
        return status;
    }

    public String getNumber() {
        return number;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
