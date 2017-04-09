package pl.com.bottega.cms.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static pl.com.bottega.cms.shared.Stubs.TEST_CINEMA_0;
import static pl.com.bottega.cms.shared.Stubs.TEST_LONG_0;

/**
 * Created by ogurekk on 2017-04-09.
 */
@RunWith(MockitoJUnitRunner.class)
public class ShowingTest {

    @Test
    public void shouldRememberIdOnCreate() {
        Cinema cinema = TEST_CINEMA_0;

        assertEquals(TEST_LONG_0, cinema.getId());
    }
}
