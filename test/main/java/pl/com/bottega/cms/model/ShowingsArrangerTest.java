package pl.com.bottega.cms.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.LinkedList;
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
                "2015-05-07T15:40",
                "2015-05-07T13:20",
                "2015-05-07T18:47",
                "2015-05-12T15:40",
                "2015-05-12T13:20",
                "2015-05-12T18:47",
                "2015-05-14T15:40",
                "2015-05-14T13:20",
                "2015-05-14T18:47",
                "2015-05-19T15:40",
                "2015-05-19T13:20",
                "2015-05-19T18:47",
                "2015-05-21T15:40",
                "2015-05-21T13:20",
                "2015-05-21T18:47",
                "2015-05-26T15:40",
                "2015-05-26T13:20",
                "2015-05-26T18:47",
                    "2015-05-28T15:40",
                    "2015-05-28T13:20",
                    "2015-05-28T18:47",
                    "2015-06-02T15:40"
            });

    @Test
    public void shouldRememberFromDate() {
        ShowingsArranger showingsArranger = TEST_SHOWINGS_ARRANGER_0;
        assertEquals(TEST_LOCAL_DATE_TIME_0, showingsArranger.getFromDate());
    }

    @Test
    public void shouldRememberUntilDate() {
        ShowingsArranger showingsArranger = TEST_SHOWINGS_ARRANGER_0;
        assertEquals(TEST_LOCAL_DATE_TIME_1, showingsArranger.getUntilDate());
    }

    @Test
    public void shouldRememberWeekDays() {
        ShowingsArranger showingsArranger = TEST_SHOWINGS_ARRANGER_0;
        Boolean testPassed = true;
        List<DayOfWeek> weekDays = showingsArranger.getWeekDays();
        for (int i=0; i<weekDays.size(); i++) {
            testPassed = weekDays.get(i).toString().equals(TEST_DAY_OF_WEEK_LIST_1.get(i));
            if (!testPassed) {
                break;
            }
        }
        assertTrue(testPassed);
    }

    @Test
    public void shouldRememberHours() {
        ShowingsArranger showingsArranger = TEST_SHOWINGS_ARRANGER_0;
        assertEquals(TEST_LOCAL_TIME_LIST_0, showingsArranger.getHours());
    }

    @Test
    public void shouldReturnShowingsList() {
        ShowingsArranger showingsArranger = TEST_SHOWINGS_ARRANGER_0;
        List<LocalDateTime> dates = showingsArranger.getDates();
        List<Showing> showings = new LinkedList<>();
        for (LocalDateTime date : dates) {
            Showing showing = new Showing();
            showing.setBeginsAt(date);
            showings.add(showing);
        }
        Boolean testPassed = showings.size() == TEST_SHOWING_LIST_RESULT_0.size();
        for (int i=0;i<showings.size();i++) {
            testPassed = (TEST_SHOWING_LIST_RESULT_0.get(i).equals(showings.get(i).getBeginsAt().toString()));
            if (!testPassed) {
                //break;
            }
        }
        assertTrue(testPassed);
    }



}
