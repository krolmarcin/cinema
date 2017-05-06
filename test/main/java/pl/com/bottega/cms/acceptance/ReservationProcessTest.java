package pl.com.bottega.cms.acceptance;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.cms.infrastructure.ReservationProcess;
import pl.com.bottega.cms.model.commands.CalculatePriceCommand;
import pl.com.bottega.cms.model.reservation.CalculationResult;
import pl.com.bottega.cms.model.reservation.PriceCalculator;
import pl.com.bottega.cms.model.reservation.ReservationItem;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class ReservationProcessTest {

    @Autowired
    private ReservationProcess reservationProcess;

    @Test
    public void shouldListAvailableSeatsForGivenShow() {

    }

    @Test
    @Sql("/fixtures/cinemaCatalog.sql")
    public void shouldCalculatePrice(){
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
