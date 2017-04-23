package pl.com.bottega.cms.model.reservation;

import sun.security.krb5.internal.Ticket;

import java.math.BigDecimal;
import java.util.Collection;

/**
 * Created by maciek on 23.04.2017.
 */
public class CalculationResult {

    private BigDecimal totalPrice;
    private Collection<Ticket> tickets;

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Collection<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Collection<Ticket> tickets) {
        this.tickets = tickets;
    }
}
