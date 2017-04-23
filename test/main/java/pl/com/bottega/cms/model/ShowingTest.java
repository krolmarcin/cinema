package pl.com.bottega.cms.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import pl.com.bottega.cms.model.showing.Showing;

import static org.junit.Assert.assertEquals;
import static pl.com.bottega.cms.shared.Stubs.*;

/**
 * Created by ogurekk on 2017-04-09.
 */
@RunWith(MockitoJUnitRunner.class)
public class ShowingTest {

    @Test
    public void shouldRememberIdOnCreate() {
        Showing showing = TEST_SHOWING_0;

        assertEquals(TEST_LONG_0, showing.getId());
    }

    @Test
    public void shouldRememberMovieOnCreate() {
        Showing showing = TEST_SHOWING_0;

        assertEquals(TEST_MOVIE_0, showing.getMovie());
    }

    @Test
    public void shouldRememberCinemaOnCreate() {
        Showing showing = TEST_SHOWING_0;

        assertEquals(TEST_CINEMA_0, showing.getCinema());
    }

}
