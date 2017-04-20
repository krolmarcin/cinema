package pl.com.bottega.cms.model.commands;


import pl.com.bottega.cms.model.ShowingsArranger;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static pl.com.bottega.cms.infrastructure.GlobalParamsAndUtils.LOCAL_DATE_TIME_FORMATTER;
import static pl.com.bottega.cms.infrastructure.GlobalParamsAndUtils.STANDARD_LOCAL_DATE_TIME_FORMATTER;
import static pl.com.bottega.cms.infrastructure.GlobalParamsAndUtils.parseStringToLocalDateTime;
import static pl.com.bottega.cms.model.commands.ValidationError.ONE_AND_ONLY_ONE;
import static pl.com.bottega.cms.model.commands.ValidationError.REQUIRED;

/**
 * Created by maciek on 09.04.2017.
 */
public class CreateShowingsCommand implements Validatable{

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
        if (areEquallyEmpty(dates, calendar))
            errors.add("dates, calendar", ONE_AND_ONLY_ONE.getValMsg());
        if (!isEmpty(calendar)) {
            if (isEmpty(calendar.getFromDate())) {
                errors.add("calendar.fromDate", REQUIRED.getValMsg());
            }
            if (isEmpty(calendar.getHours())) {
                errors.add("calendar.hours", REQUIRED.getValMsg());
            }
            if (isEmpty(calendar.getUntilDate())) {
                errors.add("calendar.untilDate", REQUIRED.getValMsg());
            }
            if (isEmpty(calendar.getWeekDays())) {
                errors.add("calendar.weekDays", REQUIRED.getValMsg());
            }
        }
        if (isEmpty(cinemaId))
            errors.add("cinemaId", REQUIRED.getValMsg());
        if (isEmpty(movieId))
            errors.add("movieId", REQUIRED.getValMsg());
    }
}
