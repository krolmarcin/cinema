package pl.com.bottega.cms.application.results.reservation;

import pl.com.bottega.cms.model.reservation.*;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

/**
 * Created by ogurekk on 2017-04-23.
 */
public class ReservationSearchResult {

    private ReservationNumber number;
    private ShowingWrapper show;
    private MovieWrapper movie;
    private List<CalculationItem> tickets;
    private Collection<DetailedSeat> seats;
    private Customer customer;
    private ReservationStatus status;
    private BigDecimal totalPrice;


    public ReservationNumber getNumber() {
        return number;
    }

    public void setNumber(ReservationNumber number) {
        this.number = number;
    }

    public ShowingWrapper getShow() {
        return show;
    }

    public void setShow(ShowingWrapper show) {
        this.show = show;
    }

    public MovieWrapper getMovie() {
        return movie;
    }

    public void setMovie(MovieWrapper movie) {
        this.movie = movie;
    }

    public List<CalculationItem> getTickets() {
        return tickets;
    }

    public void setTickets(List<CalculationItem> tickets) {
        this.tickets = tickets;
    }

    public Collection<DetailedSeat> getSeats() {
        return seats;
    }

    public void setSeats(Collection<DetailedSeat> seats) {
        this.seats = seats;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
