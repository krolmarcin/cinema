package pl.com.bottega.cms.model;


import pl.com.bottega.cms.model.reservation.ReservationItem;

import java.math.BigDecimal;
import java.util.Collection;

/**
 * Created by maciek on 23.04.2017.
 */
public class CalculationResult {

    private BigDecimal totalPrice;
    private Collection<ReservationItem> tickets;

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Collection<ReservationItem> getTickets() {
        return tickets;
    }

    public void setTickets(Collection<ReservationItem> tickets) {
        this.tickets = tickets;
    }
}
