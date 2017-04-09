package pl.com.bottega.cms.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;

import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.WEDNESDAY;
import static org.junit.Assert.assertEquals;
import static pl.com.bottega.cms.infrastructure.GlobalParamsAndUtils.LOCAL_DATE_FORMATTER;
import static pl.com.bottega.cms.shared.Stubs.TEST_LONG_0;
import static pl.com.bottega.cms.shared.Stubs.TEST_MOVIE_0;

/**
 * Created by ogurekk on 2017-04-09.
 */

@RunWith(MockitoJUnitRunner.class)
public class ShowingsArrangerTest {

    @Test
    public void shouldRememberIdOnCreate() {
        ShowingsArranger showingsArranger = new ShowingsArranger();
        showingsArranger.setFromDate(LocalDate.parse("2017/04/09", LOCAL_DATE_FORMATTER));
        showingsArranger.setUntilDate(LocalDate.parse("2017/04/20", LOCAL_DATE_FORMATTER));
        showingsArranger.setHours(Arrays.asList(new LocalTime[] {LocalTime.parse("15:40"), LocalTime.parse("16:40")}));
        showingsArranger.setWeekDays(Arrays.asList(new DayOfWeek[]{MONDAY, WEDNESDAY}));
        System.out.println(showingsArranger.getShowings());
    }


}
