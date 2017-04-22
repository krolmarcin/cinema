package pl.com.bottega.cms.model.commands;

import pl.com.bottega.cms.infrastructure.validation.Validatable;
import pl.com.bottega.cms.model.reservation.CinemaHall;
import pl.com.bottega.cms.model.reservation.Customer;
import pl.com.bottega.cms.model.reservation.DetailedSeat;
import pl.com.bottega.cms.model.reservation.ReservationItem;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by ogurekk on 2017-04-22.
 */
public class CreateReservationCommand implements Validatable{

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

    @Override
    public void validate(ValidationErrors errors) {
        ensureNotEmpty(tickets, "tickets", errors);
        ensureAtLeastX(tickets, "tickets", 1, errors);
        List<String> ticketTypes = new ArrayList<>();
        for (ReservationItem reservationItem : tickets) {
            ticketTypes.add(reservationItem.getKind());
        }
        ensureUniqueElements(ticketTypes, "tickets", errors);
        CinemaHall cinemaHall = CinemaHall.STANDARD;
        cinemaHall.ensureReservationCompatible(seats);
        ensureNotEmpty(seats, "seats", errors);
        ensureNotEmpty(customer, "customer", errors);
        if (!isEmpty(customer)) {
            ensureNotEmpty(customer.getFirstName(), "customer.firstName", errors);
            ensureNotEmpty(customer.getLastName(), "customer.lastName", errors);
            ensureNotEmpty(customer.getEmail(), "customer.email", errors);
            ensureNotEmpty(customer.getPhone(), "customer.phone", errors);
        }

    }
}
