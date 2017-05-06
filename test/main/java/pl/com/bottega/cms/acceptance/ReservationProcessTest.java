package pl.com.bottega.cms.acceptance;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.cms.infrastructure.ReservationProcess;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class ReservationProcessTest {

    @Autowired
    private ReservationProcess reservationProcess;

    @Test
    public void shouldListAvailableSeatsForGivenShow() {

    }

}
