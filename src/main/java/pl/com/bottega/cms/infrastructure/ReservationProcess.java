package pl.com.bottega.cms.infrastructure;

import pl.com.bottega.cms.application.dtos.CinemaHallDto;
import pl.com.bottega.cms.model.commands.CreatePaymentCommand;
import pl.com.bottega.cms.model.reservation.CalculationResult;
import pl.com.bottega.cms.model.reservation.ReservationNumber;
import pl.com.bottega.cms.model.commands.CalculatePriceCommand;
import pl.com.bottega.cms.model.commands.CreateReservationCommand;

/**
 * Created by ogurekk on 2017-04-22.
 */
public interface ReservationProcess {

    CalculationResult calculatePrices(CalculatePriceCommand cmd);

    ReservationNumber create(CreateReservationCommand cmd);

    CinemaHallDto getSeats(Long showingId);

    void createPayment(CreatePaymentCommand cmd);

}
