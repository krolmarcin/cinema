package pl.com.bottega.cms.model.showing;

import pl.com.bottega.cms.model.cinema.Cinema;
import pl.com.bottega.cms.model.commands.CreateShowingsCommand;
import pl.com.bottega.cms.model.movie.Movie;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import static pl.com.bottega.cms.infrastructure.GlobalParamsAndUtils.STANDARD_LOCAL_DATE_TIME_FORMATTER;

/**
 * Created by maciek on 09.04.2017.
 */
public class ShowingsFactory {

    public ShowingsFactory() {

    }

    public List<Showing> createShowings(Movie movie, Cinema cinema, CreateShowingsCommand cmd) {
        List<Showing> showings = new ArrayList<>();
        ShowingsArranger calendar = cmd.getCalendar();
        if (calendar != null && calendar.getDates() == null) {
            calculateDatesForShowing(calendar);
        }
        List<LocalDateTime> dates = cmd.getDates();
        for (LocalDateTime date : dates) {
            Showing showing = new Showing();
            showing.setBeginsAt(date);
            showing.setMovie(movie);
            showing.setCinema(cinema);
            showing.setReservations(new HashSet<>());
            showings.add(showing);
        }
        return showings;
    }


    private void calculateDatesForShowing(ShowingsArranger showingsArranger) {
        LocalDateTime fromDate = showingsArranger.getFromDate();
        LocalDateTime untilDate = showingsArranger.getUntilDate();
        List<DayOfWeek> weekDays = showingsArranger.getWeekDays();
        List<LocalTime> hours = showingsArranger.getHours();
        List<LocalDateTime> dates = new LinkedList<>();
        LocalDateTime actualDate = fromDate;
        while (actualDate.compareTo(untilDate) < 0) {
            DayOfWeek dayOfWeek = actualDate.getDayOfWeek();
            if (weekDays.contains(dayOfWeek)) {
                for (LocalTime hour : hours) {
                    dates.add(LocalDateTime.parse(actualDate.toString().substring(0,10) + " " + hour.toString(), STANDARD_LOCAL_DATE_TIME_FORMATTER));
                }

            }
            actualDate = actualDate.plusDays(1);
        }
        if (actualDate.compareTo(untilDate) == 0) {
            for (LocalTime hour : hours) {
                if (("" + actualDate.getHour() + actualDate.getMinute()).compareTo("" + hour.getHour() + hour.getMinute()) >= 0) {
                    dates.add(LocalDateTime.parse(actualDate.toString().substring(0,10) + " " + hour.toString(), STANDARD_LOCAL_DATE_TIME_FORMATTER));
                }
            }
        }
        showingsArranger.setDates(dates);
    }
}
