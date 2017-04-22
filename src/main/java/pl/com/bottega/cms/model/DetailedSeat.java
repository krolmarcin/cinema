package pl.com.bottega.cms.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by ogurekk on 2017-04-21.
 */

@Entity
public class DetailedSeat {

    @Id
    @GeneratedValue
    private int id;

    private Integer row;
    private Integer seat;

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Integer getSeat() {
        return seat;
    }

    public void setSeat(Integer seat) {
        this.seat = seat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (this == null || o == null) {
            return false;
        }
        if (! (o instanceof DetailedSeat)) {
            return false;
        }
        DetailedSeat detailedSeat = (DetailedSeat) o;
        return this.row == detailedSeat.row && this.seat == detailedSeat.seat;

    }

}
