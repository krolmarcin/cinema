package pl.com.bottega.cms.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Created by ogurekk on 2017-04-21.
 */

@Entity
public class Reservation {

    @GeneratedValue
    private Integer id;

    @EmbeddedId
    @AttributeOverride(name = "number", column = @Column(name = "reservation_number"))
    private ReservationNumber reservationNumber;

    private ReservationStatus reservationStatus;

    @ElementCollection
    private Set<ReservationItem> reservationItems;

    @Transient
    private Set<DetailedSeat> detailedSeats;

    @Embedded
    private Customer customer;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ReservationNumber getReservationNumber() {
        return reservationNumber;
    }

    public void setReservationNumber(ReservationNumber reservationNumber) {
        this.reservationNumber = reservationNumber;
    }

    public ReservationStatus getReservationStatus() {
        return reservationStatus;
    }

    public void setReservationStatus(ReservationStatus reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

    public Set<ReservationItem> getTicketTypeList() {
        return reservationItems;
    }

    public void setTicketTypeList(Set<ReservationItem> reservationItems) {
        this.reservationItems = reservationItems;
    }

    public Set<DetailedSeat> getDetailedSeats() {
        return detailedSeats;
    }

    public void setDetailedSeats(Set<DetailedSeat> detailedSeats) {
        this.detailedSeats = detailedSeats;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
