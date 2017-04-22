package pl.com.bottega.cms.model;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by ogurekk on 2017-04-21.
 */

@Embeddable
public class ReservationNumber implements Serializable{

    private String number;

    public ReservationNumber(String number) {
        this.number = number;
    }

    public ReservationNumber() {

    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

}
