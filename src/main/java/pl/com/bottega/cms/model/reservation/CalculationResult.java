package pl.com.bottega.cms.model.reservation;



import java.math.BigDecimal;
import java.util.Set;

/**
 * Created by maciek on 23.04.2017.
 */
public class CalculationResult {

    private Set<CalculationItem> tickets;
    private BigDecimal totalPrice;

    public CalculationResult(Set<CalculationItem> tickets) {
        this.tickets = tickets;
        this.totalPrice = calculateTotalPrice(tickets);
    }

    private BigDecimal calculateTotalPrice(Set<CalculationItem> tickets){
        BigDecimal total = BigDecimal.ZERO;
        for (CalculationItem ticket : tickets){
            total = total.add(ticket.getTotalPrice());
        }
        return total;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public Set<CalculationItem> getTickets() {
        return tickets;
    }

}
