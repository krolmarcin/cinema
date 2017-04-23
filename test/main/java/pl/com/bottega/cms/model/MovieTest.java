package pl.com.bottega.cms.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import pl.com.bottega.cms.model.movie.Movie;
import pl.com.bottega.cms.model.movie.Pricing;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static pl.com.bottega.cms.shared.Stubs.*;

/**
 * Created by ogurekk on 2017-04-09.
 */
@RunWith(MockitoJUnitRunner.class)
public class MovieTest {

    @Test
    public void shouldRememberIdOnCreate() {
        Movie movie = TEST_MOVIE_0;

        assertEquals(TEST_LONG_0, movie.getId());
    }

    @Test
    public void shouldRememberTitleOnCreate() {
        Movie movie = TEST_MOVIE_0;

        assertEquals(TEST_STRING_0, movie.getTitle());
    }

    @Test
    public void shouldRememberDescriptionOnCreate() {
        Movie movie = TEST_MOVIE_0;

        assertEquals(TEST_STRING_1, movie.getDescription());
    }

    @Test
    public void shouldRememberActorsOnCreate() {
        Movie movie = TEST_MOVIE_0;

        assertEquals(TEST_STRING_SET_0, movie.getActors());
    }

    @Test
    public void shouldRememberGenresOnCreate() {
        Movie movie = TEST_MOVIE_0;

        assertEquals(TEST_STRING_SET_1, movie.getGenres());
    }

    @Test
    public void shouldRememberMinAgeOnCreate() {
        Movie movie = TEST_MOVIE_0;

        assertEquals(TEST_INT_0, movie.getMinAge());
    }


    @Test
    public void shouldRememberLengthOnCreate() {
        Movie movie = TEST_MOVIE_0;

        assertEquals(TEST_INT_1, movie.getLength());
    }

    @Test
    public void shouldRememberPriceOnDefine() {
        Movie movie = TEST_MOVIE_0;

        assertTrue(movie.getPricing().getPriceMap().containsKey(TEST_STRING_0));
        assertEquals(TEST_BIGDECIMAL_0, movie.getPricing().getPriceMap().get(TEST_STRING_0));
        assertEquals(TEST_BIGDECIMAL_1, movie.getPricing().getPriceMap().get(TEST_STRING_1));
    }

}
