package pl.com.bottega.cms.model.reservation;

import javax.persistence.Column;
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

    @Column(name = "number")
    private Long count;

    public ReservationItem() {
    }

    public ReservationItem(String kind, Long count) {
        this.kind = kind;
        this.count = count;
    }

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
        return count;
    }

    public void setNumber(Long number) {
        this.count = number;
    }

    public void setCount(Long count) {
        this.count = count;
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
