package pl.com.bottega.cms.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Created by ogurekk on 2017-04-21.
 */

@Entity
public class Reservation {

    @Id
    @GeneratedValue
    private Long id;

    @Embedded
    @AttributeOverride(name = "number", column = @Column(name = "reservation_number"))
    private ReservationNumber reservationNumber;

    private ReservationStatus reservationStatus;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "reservation_id")
    private Set<ReservationItem> reservationItems;

    @Transient
    private Set<DetailedSeat> detailedSeats;

    @Embedded
    private Customer customer;

    public Reservation() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Set<ReservationItem> getReservationItems() {
        return reservationItems;
    }

    public void setReservationItems(Set<ReservationItem> reservationItems) {
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
