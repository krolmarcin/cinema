package pl.com.bottega.cms.acceptance;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.cms.application.dtos.CinemaHallDto;
import pl.com.bottega.cms.infrastructure.processes.ReservationProcess;
import pl.com.bottega.cms.model.commands.CalculatePriceCommand;
import pl.com.bottega.cms.model.reservation.CalculationResult;
import pl.com.bottega.cms.model.reservation.DetailedSeat;
import pl.com.bottega.cms.model.reservation.ReservationItem;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class ReservationProcessTest {

    @Autowired
    private ReservationProcess reservationProcess;

    @Test
    @Sql("/fixtures/cinemaCatalog.sql")
    public void shouldListAvailableSeatsForGivenShow() {
        Long showingId = 1L;

        CinemaHallDto cinemaHallDto = reservationProcess.getSeats(showingId);
        List<DetailedSeat> detailedSeats = cinemaHallDto.getFree();

        assertThat(cinemaHallDto.getFree()).isNotNull();
        assertThat(detailedSeats.size()).isEqualTo(147);

        Boolean detailedFreeSeatFound = false;
        for (DetailedSeat detailedSeat : detailedSeats) {
            if (detailedSeat.getRow() == 2 && detailedSeat.getSeat() == 2) {
                detailedFreeSeatFound = true;
                break;
            }
        }
        assertTrue(detailedFreeSeatFound);
    }

    @Test
    @Sql("/fixtures/cinemaCatalog.sql")
    public void shouldListUnavailableSeatsForGivenShow() {
        Long showingId = 2L;

        CinemaHallDto cinemaHallDto = reservationProcess.getSeats(showingId);
        List<DetailedSeat> detailedSeats = cinemaHallDto.getOccupied();

        assertThat(cinemaHallDto.getOccupied()).isNotNull();

        Boolean detailedOccupiedSeatFound = false;
        for (DetailedSeat detailedSeat : detailedSeats) {
            if (detailedSeat.getRow() == 10 && detailedSeat.getSeat() == 15) {
                detailedOccupiedSeatFound = true;
                break;
            }
        }
        assertTrue(detailedOccupiedSeatFound);
        assertThat(detailedSeats.size()).isEqualTo(3);
    }

    @Test
    @Sql("/fixtures/cinemaCatalog.sql")
    public void shouldCalculatePrice() {
        CalculatePriceCommand cmd = new CalculatePriceCommand();
        cmd.setShowId(1L);
        ReservationItem reservationItem = new ReservationItem();
        reservationItem.setKind("regular");
        reservationItem.setNumber(5L);
        Set<ReservationItem> reservationItems = new HashSet<>();
        reservationItems.add(reservationItem);
        cmd.setTickets(reservationItems);

        CalculationResult calculationResult = reservationProcess.calculatePrices(cmd);

        assertThat(calculationResult.getTickets().size()).isEqualTo(1);
        assertThat(calculationResult.getTickets().contains("regular"));
        assertThat(calculationResult.getTotalPrice()).isEqualTo(new BigDecimal(21.25));
    }

}