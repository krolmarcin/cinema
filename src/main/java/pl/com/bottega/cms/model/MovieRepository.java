package pl.com.bottega.cms.model;

/**
 * Created by maciek on 09.04.2017.
 */
public interface MovieRepository {
    void put(Movie m);

    Movie get(Long id);

    void putTicketPrice(TicketPrice ticketPrice);

    void updateTicketPrice(TicketPrice ticketPrice);

    TicketPrice getTicketPriceFor(Movie movie);

    boolean checkIfExistPriceFor(Movie movie);
}
