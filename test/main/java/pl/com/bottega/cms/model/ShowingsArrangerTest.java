package pl.com.bottega.cms.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static pl.com.bottega.cms.shared.Stubs.*;

/**
 * Created by ogurekk on 2017-04-09.
 */

@RunWith(MockitoJUnitRunner.class)
public class ShowingsArrangerTest {

    private final List<String> TEST_SHOWING_LIST_RESULT_0 = Arrays.asList(new String[]
            {
                    "2017-04-11T15:40",
                    "2017-04-11T13:20",
                    "2017-04-11T18:47",
                    "2017-04-13T15:40",
                    "2017-04-13T13:20",
                    "2017-04-13T18:47",
                    "2017-04-18T15:40",
                    "2017-04-18T13:20",
                    "2017-04-18T18:47",
                    "2017-04-20T15:40",
                    "2017-04-20T13:20",
                    "2017-04-20T18:47"
            });

    @Test
    public void shouldRememberFromDate() {
        ShowingsArranger showingsArranger = TEST_SHOWINGS_ARRANGER_0;
        assertEquals(TEST_LOCAL_DATE_0, TEST_SHOWINGS_ARRANGER_0.getFromDate());
    }

    @Test
    public void shouldRememberUntilDate() {
        ShowingsArranger showingsArranger = TEST_SHOWINGS_ARRANGER_0;
        assertEquals(TEST_LOCAL_DATE_1, TEST_SHOWINGS_ARRANGER_0.getUntilDate());
    }

    @Test
    public void shouldRememberWeekDays() {
        ShowingsArranger showingsArranger = TEST_SHOWINGS_ARRANGER_0;
        assertEquals(TEST_DAY_OF_WEEK_LIST_1, TEST_SHOWINGS_ARRANGER_0.getWeekDays());
    }

    @Test
    public void shouldRememberHours() {
        ShowingsArranger showingsArranger = TEST_SHOWINGS_ARRANGER_0;
        assertEquals(TEST_LOCAL_TIME_LIST_0, TEST_SHOWINGS_ARRANGER_0.getHours());
    }

    @Test
    public void shouldReturnShowingsList() {
        ShowingsArranger showingsArranger = TEST_SHOWINGS_ARRANGER_0;
        List<Showing> showings = TEST_SHOWINGS_ARRANGER_0.getShowings();
        Boolean testPassed = true;
        for (int i=0;i<showings.size();i++) {
            testPassed = (TEST_SHOWING_LIST_RESULT_0.get(i).equals(showings.get(i).getBeginsAt().toString()));
            if (!testPassed) {
                break;
            }
        }
        assertTrue(testPassed);
    }



}
