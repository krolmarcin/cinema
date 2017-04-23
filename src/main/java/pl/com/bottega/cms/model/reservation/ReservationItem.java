package pl.com.bottega.cms.model.reservation;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

/**
 * Created by ogurekk on 2017-04-22.
 */
@Entity
public class ReservationItem {
    @Id
    @GeneratedValue
    private Long id;

    private String kind;

    private Long number;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (this == null || o == null) {
            return false;
        }
        if (!(o instanceof ReservationItem)) {
            return false;
        }
        ReservationItem reservationItem = (ReservationItem) o;
        return (this.kind.equals(reservationItem.kind));
    }
}