package pl.com.bottega.cms.acceptance;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.cms.application.CinemaCatalog;
import pl.com.bottega.cms.application.CinemaDto;
import pl.com.bottega.cms.model.Cinema;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static pl.com.bottega.cms.shared.Stubs.*;
import pl.com.bottega.cms.application.AdminPanel;

import java.util.List;

/**
 * Created by ogurekk on 2017-04-09.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class AdminPanelTest {

    @Autowired
    private AdminPanel adminPanel;

    @Autowired
    private CinemaCatalog cinemaCatalog;

    @Test
    public void shouldCreateCinema() {
        adminPanel.createCinema(TEST_CREATE_CINEMA_COMMAND_0);
        List<CinemaDto> cinemaDtos = cinemaCatalog.getCinemas();
        Boolean cinemaFound = false;
        for (CinemaDto cinemaDto : cinemaDtos) {
            if (cinemaDto.getName() == TEST_STRING_0 && cinemaDto.getCity() == TEST_STRING_1) {
                cinemaFound = true;
                break;
            }
        }
        assertTrue(cinemaFound);
    }
}
