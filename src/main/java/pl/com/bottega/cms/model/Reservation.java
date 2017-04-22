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
    @Column(name = "resevration_id")
    private Integer id;

    @EmbeddedId
    @AttributeOverride(name = "number", column = @Column(name = "reservation_number"))
    private ReservationNumber reservationNumber;

    private ReservationStatus reservationStatus;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "resevration_id")
    private List<String> ticketTypeList;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "reservation_id")
    private Set<DetailedSeat> seats;

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

    public List<String> getTicketTypeList() {
        return ticketTypeList;
    }

    public void setTicketTypeList(List<String> ticketTypeList) {
        this.ticketTypeList = ticketTypeList;
    }

    public Set<DetailedSeat> getSeats() {
        return seats;
    }

    public void setSeats(Set<DetailedSeat> seats) {
        this.seats = seats;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
