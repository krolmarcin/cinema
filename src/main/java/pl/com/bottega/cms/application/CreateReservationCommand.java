package pl.com.bottega.cms.application;

import pl.com.bottega.cms.model.Customer;
import pl.com.bottega.cms.model.DetailedSeat;
import pl.com.bottega.cms.model.ReservationItem;

import java.util.Set;

/**
 * Created by ogurekk on 2017-04-22.
 */
public class CreateReservationCommand {

    private Long showId;

    private Set<ReservationItem> tickets;

    private Set<DetailedSeat> seats;

    private Customer customer;

    public Long getShowId() {
        return showId;
    }

    public void setShowId(Long showId) {
        this.showId = showId;
    }

    public Set<ReservationItem> getTickets() {
        return tickets;
    }

    public void setTickets(Set<ReservationItem> tickets) {
        this.tickets = tickets;
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
