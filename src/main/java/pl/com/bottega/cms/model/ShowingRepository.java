package pl.com.bottega.cms.model;

import java.util.List;

/**
 * Created by maciek on 09.04.2017.
 */
public interface ShowingRepository {
    void put(Showing s);

    Showing get(Long id);

    List<Reservation> getReservations(Long showingId);

}
