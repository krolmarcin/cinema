package pl.com.bottega.cms.model.repositories;

import pl.com.bottega.cms.model.reservation.Reservation;
import pl.com.bottega.cms.model.reservation.ReservationNumber;

/**
 * Created by maciek on 06.05.2017.
 */
public interface ReservationRepository {

    Reservation get(ReservationNumber number);
}
