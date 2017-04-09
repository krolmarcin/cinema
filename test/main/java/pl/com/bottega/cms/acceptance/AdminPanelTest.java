package pl.com.bottega.cms.acceptance;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.cms.model.Cinema;

import static pl.com.bottega.cms.shared.Stubs.*;

/**
 * Created by ogurekk on 2017-04-09.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class AdminPanelTest {

    @Autowired
    private AdminPanel adminPanel;

    @

    @Test
    public void shouldCreateCinema() {
        Cinema cinema = TEST_CINEMA_0;

    }
}
