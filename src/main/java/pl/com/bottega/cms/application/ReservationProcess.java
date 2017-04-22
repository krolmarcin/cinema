package pl.com.bottega.cms.application;

import pl.com.bottega.cms.model.CinemaHallDto;
import pl.com.bottega.cms.model.ReservationNumber;

/**
 * Created by ogurekk on 2017-04-22.
 */
public interface ReservationProcess {

    CalculationResult calculatePrices(CalculatePriceCommand cmd);
    ReservationNumber create(CreateReservationCommand cmd);
    CinemaHallDto getSeats(Long showingId);

}
