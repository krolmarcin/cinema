package pl.com.bottega.cms.application.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.cms.model.reservation.CalculationResult;
import pl.com.bottega.cms.application.dtos.CinemaHallDto;
import pl.com.bottega.cms.model.repositories.ShowingRepository;
import pl.com.bottega.cms.model.commands.CalculatePriceCommand;
import pl.com.bottega.cms.model.commands.CreateReservationCommand;
import pl.com.bottega.cms.infrastructure.processes.ReservationProcess;
import pl.com.bottega.cms.model.reservation.*;
import pl.com.bottega.cms.model.showing.Showing;

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
    private PriceCalculator priceCalculator;


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


}