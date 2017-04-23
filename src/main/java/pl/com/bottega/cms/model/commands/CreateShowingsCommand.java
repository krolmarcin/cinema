package pl.com.bottega.cms.model.commands;


import pl.com.bottega.cms.model.showing.ShowingsArranger;
import pl.com.bottega.cms.infrastructure.validation.Validatable;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import static pl.com.bottega.cms.infrastructure.GlobalParamsAndUtils.parseStringToLocalDateTime;

/**
 * Created by maciek on 09.04.2017.
 */
public class CreateShowingsCommand implements Validatable {

    private List<LocalDateTime> dates;

    private Long cinemaId;

    private ShowingsArranger calendar;

    private Long movieId;

    public List<LocalDateTime> getDates() {
        if (calendar != null) {
            return calendar.getDates();
        }
        return dates;
    }

    public void setDates(List<String> dates) {
        List<LocalDateTime> datesLocalDateTime = new LinkedList<>();
        for (String date : dates) {
            datesLocalDateTime.add(parseStringToLocalDateTime(date));
        }
        this.dates = datesLocalDateTime;
    }

    public void setDatesLocalDateTime(List<LocalDateTime> dates) {
        this.dates = dates;
    }

    public void setCinemaId(Long cinemaId) {
        this.cinemaId = cinemaId;
    }

    public Long getCinemaId() {
        return cinemaId;
    }

    public ShowingsArranger getCalendar() {
        return calendar;
    }

    public void setCalendar(ShowingsArranger calendar) {
        this.calendar = calendar;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }


    @Override
    public void validate(ValidationErrors errors) {
        ensureNotEquallyEmpty(new Object[] {dates, calendar}, new String[] {"dates", "calendar"}, errors);
        if (!isEmpty(calendar)) {
            ensureNotEmpty(calendar.getFromDate(), "calendar.fromDate", errors);
            ensureNotEmpty(calendar.getHours(), "calendar.hours", errors);
            ensureNotEmpty(calendar.getUntilDate(), "calendar.untilDate", errors);
            ensureNotEmpty(calendar.getWeekDays(), "calendar.weekDays", errors);
        }
        ensureNotEmpty(cinemaId, "cinemaId", errors);
        ensureNotEmpty(movieId, "movieId", errors);
    }
}
