package pl.com.bottega.cms.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.Mockito.*;

import static org.junit.Assert.assertEquals;
import static pl.com.bottega.cms.shared.Stubs.*;


/**
 * Created by ogurekk on 2017-04-09.
 */
@RunWith(MockitoJUnitRunner.class)
public class CinemaTest {

    @Test
    public void shouldRememberIdOnCreate() {
        Cinema cinema = TEST_CINEMA_0;

        assertEquals(TEST_LONG_0, cinema.getId());
    }

    @Test
    public void shouldRememberNameOnCreate() {
        Cinema cinema = TEST_CINEMA_0;

        assertEquals(TEST_STRING_0, cinema.getName());
    }

    @Test
    public void shouldRememberCityOnCreate() {
        Cinema cinema = TEST_CINEMA_0;

        assertEquals(TEST_STRING_1, cinema.getCity());
    }

}
