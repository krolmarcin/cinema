package pl.com.bottega.cms.model.commands;

import pl.com.bottega.cms.infrastructure.validation.Validatable;
import pl.com.bottega.cms.model.movie.Pricing;
import pl.com.bottega.cms.model.reservation.ReservationItem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by ogurekk on 2017-04-22.
 */
public class CalculatePriceCommand implements Validatable {

    private Long showId;
    private Set<ReservationItem> tickets;

    public void setShowId(Long showId) {
        this.showId = showId;
    }

    public void setTickets(Set<ReservationItem> tickets) {
        this.tickets = tickets;
    }

    public Long getShowId() {
        return showId;
    }

    public Set<ReservationItem> getTickets() {
        return tickets;
    }

    @Override
    public void validate(ValidationErrors errors) {
        ensureNotEmpty(showId, "showId", errors);
        if (isEmpty(tickets) || tickets.size() == 0)
            errors.add("tickets", "can't be empty");
        else {
            ensureAtLeastX(tickets, "tickets", 1, errors);
            List<String> ticketTypes = new ArrayList<>();
            for (ReservationItem ticket : tickets) {
                if (ticket.getKind().equals("regular") || ticket.getKind().equals("student") ||
                        ticket.getKind().equals("school") || ticket.getKind().equals("children"))
                    ticketTypes.add(ticket.getKind());
                else
                    errors.add("tickets", String.format("not recognized kind of ticket: %s", ticket.getKind()));
                if (ticket.getNumber() <= 0)
                    errors.add("number", "number of ticket can't be less or equal 0");
            }
            ensureUniqueElements(ticketTypes, "tickets", errors);
        }
    }
}
