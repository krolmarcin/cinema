package pl.com.bottega.cms.model.commands;

import pl.com.bottega.cms.infrastructure.validation.Validatable;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by ogurekk on 2017-04-22.
 */
public class CalculatePriceCommand implements Validatable{

    private Long showId;
    private Set<Ticket> tickets;


    public Long getShowId() {
        return showId;
    }

    public void setShowId(Long showId) {
        this.showId = showId;
    }

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }

    @Override
    public void validate(ValidationErrors errors) {
        ensureNotEmpty(showId, "showId", errors);
        ensureNotEmpty(tickets, "tickets", errors);
        ensureAtLeastX(tickets, "tickets", 1, errors);
        List<String> ticketTypes = new ArrayList<>();
        for (Ticket ticket : tickets) {
            ticketTypes.add(ticket.getKind());
        }
        ensureUniqueElements(ticketTypes, "tickets", errors);

    }

    private class Ticket{
        private String kind;
        private Integer count;

        public Ticket(String kind, Integer count) {
            this.kind = kind;
            this.count = count;
        }

        public String getKind() {
            return kind;
        }

        public void setKind(String kind) {
            this.kind = kind;
        }

        public Integer getCount() {
            return count;
        }

        public void setCount(Integer count) {
            this.count = count;
        }
    }
}
