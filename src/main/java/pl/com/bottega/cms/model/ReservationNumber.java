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

    @GeneratedValue
    private int number;
}
