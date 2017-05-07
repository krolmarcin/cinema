package pl.com.bottega.cms.application.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.cms.application.catalogs.ReservationCatalog;
import pl.com.bottega.cms.model.commands.CollectPaymentCommand;
import pl.com.bottega.cms.model.repositories.ReservationRepository;
import pl.com.bottega.cms.model.reservation.CalculationResult;
import pl.com.bottega.cms.application.dtos.CinemaHallDto;
import pl.com.bottega.cms.model.repositories.ShowingRepository;
import pl.com.bottega.cms.model.commands.CalculatePriceCommand;
import pl.com.bottega.cms.model.commands.CreateReservationCommand;
import pl.com.bottega.cms.model.repositories.ReservationProcess;
import pl.com.bottega.cms.model.reservation.*;
import pl.com.bottega.cms.model.showing.Showing;
import pl.com.bottega.cms.ui.InvalidActionException;

/**
 * Created by ogurekk on 2017-04-22.
 */
@Transactional
public class StandardReservationProcess implements ReservationProcess {

    @Autowired
    private ShowingRepository showingRepository;

    @Autowired
    private ReservationNumberGenerator reservationNumberGenerator;

    @Autowired
    private ReservationCatalog reservationCatalog;

    @Autowired
    private PriceCalculator priceCalculator;

    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    public CalculationResult calculatePrices(CalculatePriceCommand cmd) {
        return priceCalculator.calculatePrices(cmd);
    }

    @Override
    public ReservationNumber create(CreateReservationCommand cmd) {
        Showing showing = showingRepository.get(cmd.getShowId());
        ReservationNumber reservationNumber = showing.createReservation(cmd, reservationNumberGenerator);
        showingRepository.put(showing);
        return reservationNumber;
    }

    @Override
    public CinemaHallDto getSeats(Long showingId) {
        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.updateSeatConfiguration(showingRepository.getReservations(showingId));
        CinemaHallDto cinemaHallDto = new CinemaHallDto(cinemaHall.getSeatConfiguration());
        return cinemaHallDto;
    }

    @Override
    public void collectPayment(CollectPaymentCommand cmd) {
        Reservation reservation = reservationRepository.get(cmd.getReservationNumber());
        if (reservation == null)
            throw new InvalidActionException("There is no reservation");
        if (reservation.getReservationStatus().equals(ReservationStatus.PAID) || reservation.getReservationStatus().equals(ReservationStatus.CANCELLED))
            ;
        throw new InvalidActionException("Reservation was paid or cancelled");


    }


}
