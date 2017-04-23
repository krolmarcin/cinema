package pl.com.bottega.cms.model.commands;

import pl.com.bottega.cms.infrastructure.validation.Validatable;
import pl.com.bottega.cms.model.reservation.ReservationItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by ogurekk on 2017-04-22.
 */
public class CalculatePriceCommand implements Validatable{

    private Long showId;
    private Set<ReservationItem> tickets;


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

    @Override
    public void validate(ValidationErrors errors) {
        ensureNotEmpty(showId, "showId", errors);
        ensureNotEmpty(tickets, "tickets", errors);
        ensureAtLeastX(tickets, "tickets", 1, errors);
        List<String> ticketTypes = new ArrayList<>();
        for (ReservationItem reservationItem : tickets) {
            ticketTypes.add(reservationItem.getKind());
        }
        ensureUniqueElements(ticketTypes, "tickets", errors);

    }
}
