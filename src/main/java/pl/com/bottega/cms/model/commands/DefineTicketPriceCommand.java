package pl.com.bottega.cms.model.commands;

import java.math.BigDecimal;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by maciek on 15.04.2017.
 */
public class DefineTicketPriceCommand implements Validatable {

    private Map<String, BigDecimal> ticketPrices;
    private Long movieId;


    public Map<String, BigDecimal> getTicketPrices() {
        return ticketPrices;
    }

    public void setTicketPrices(Map<String, BigDecimal> ticketPrices) {
        this.ticketPrices = ticketPrices;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    @Override
    public void validate(ValidationErrors errors) {
        if (ticketPrices == null || ticketPrices.size() == 0)
            errors.add("ticketPrices", "can't be empty");
        if (!ticketPrices.containsKey("regular"))
            errors.add("regular", "is required field");
        else if (!Pattern.matches("\\d+\\.\\d{2}", ticketPrices.get("regular").toString()))
            errors.add("regular", "must be number of structure 999.99 and can't be less than 0");
        if (!ticketPrices.containsKey("student"))
            errors.add("student", "is required field");
        else if (!Pattern.matches("^\\d{1,5}\\.\\d{2}$", ticketPrices.get("student").toString()))
            errors.add("student", "must be number of structure 999.99 and can't be less than 0");
        if (ticketPrices.containsKey("school")) {
            if (!Pattern.matches("\\d+\\.\\d{2}", ticketPrices.get("school").toString()))
                errors.add("school", "must be number of structure 999.99 and can't be less than 0");
        }
        if (ticketPrices.containsKey("children")) {
            if (!Pattern.matches("\\d+\\.\\d{2}", ticketPrices.get("children").toString()))
                errors.add("children", "must be number of structure 999.99 and can't be less than 0");
        }
    }
}
