package pl.com.bottega.cms.application.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.cms.application.dtos.CinemaHallDto;
import pl.com.bottega.cms.infrastructure.repositories.ShowingRepository;
import pl.com.bottega.cms.model.commands.CalculatePriceCommand;
import pl.com.bottega.cms.application.CalculationResult;
import pl.com.bottega.cms.model.commands.CreateReservationCommand;
import pl.com.bottega.cms.infrastructure.ReservationProcess;
import pl.com.bottega.cms.model.reservation.*;
import pl.com.bottega.cms.model.showing.Showing;

import java.util.Set;

/**
 * Created by ogurekk on 2017-04-22.
 */
@Transactional
public class StandardReservationProcess implements ReservationProcess {

    @Autowired
    private ShowingRepository showingRepository;

    @Autowired
    private ReservationNumberGenerator reservationNumberGenerator;

    @Override
    public CalculationResult calculatePrices(CalculatePriceCommand cmd) {
        return null;
    }

    @Override
    public ReservationNumber create(CreateReservationCommand cmd) {
        Long showingId = cmd.getShowId();
        Set<ReservationItem> reservationItems = cmd.getTickets();
        Set<DetailedSeat> detailedSeats = cmd.getSeats();
        Customer customer = cmd.getCustomer();
        Reservation reservation = new Reservation();
        reservation.setReservationItems(reservationItems);
        reservation.setDetailedSeats(detailedSeats);
        reservation.setCustomer(customer);
        Showing showing = showingRepository.get(cmd.getShowId());
        reservation.setReservationNumber(new ReservationNumber(reservationNumberGenerator.generate(showing.getCinema().getId(), showing.getMovie().getId(), showing.getBeginsAt(), showing.getReservations().size())));
        showing.getReservations().add(reservation);
        showingRepository.put(showing);
        return reservation.getReservationNumber();
    }

    @Override
    public CinemaHallDto getSeats(Long showingId) {
        CinemaHall cinemaHall = CinemaHall.STANDARD;
        cinemaHall.updateSeatConfiguration(showingRepository.getReservations(showingId));
        CinemaHallDto cinemaHallDto = new CinemaHallDto(cinemaHall.getSeatConfiguration());
        return cinemaHallDto;
    }
}
