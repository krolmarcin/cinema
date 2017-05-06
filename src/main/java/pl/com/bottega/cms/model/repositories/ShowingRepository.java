package pl.com.bottega.cms.model.repositories;

import pl.com.bottega.cms.model.reservation.Reservation;
import pl.com.bottega.cms.model.showing.Showing;

import java.util.List;
import java.util.Set;

/**
 * Created by maciek on 09.04.2017.
 */
public interface ShowingRepository {
    void put(Showing s);

    Showing get(Long id);

    Set<Reservation> getReservations(Long showingId);

}
