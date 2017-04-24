package pl.com.bottega.cms.model.reservation;

import pl.com.bottega.cms.infrastructure.repositories.ShowingRepository;
import pl.com.bottega.cms.model.commands.CalculatePriceCommand;
import pl.com.bottega.cms.model.showing.Showing;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by maciek on 23.04.2017.
 */
public class PriceCalculator {

    private ShowingRepository showingRepository;

    public PriceCalculator(ShowingRepository showingRepository) {
        this.showingRepository = showingRepository;
    }

    public CalculationResult calculatePrices(CalculatePriceCommand cmd) {
        Set<CalculationItem> calculationItems = new HashSet<>();
        Showing showing = showingRepository.get(cmd.getShowId());
        Map<String, BigDecimal> pricing = showing.getMovie().getPricing().getPriceMap();
        Set<ReservationItem> tickets = cmd.getTickets();
        for (ReservationItem ticket : tickets) {
            String kind = ticket.getKind();
            BigDecimal unitPrice = pricing.get(kind);
            CalculationItem calculationItem = new CalculationItem(ticket, unitPrice);
            calculationItems.add(calculationItem);
        }
        return new CalculationResult(calculationItems);
    }
}
