package pl.com.bottega.cms.model;

import pl.com.bottega.cms.model.commands.Validatable;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

import static pl.com.bottega.cms.infrastructure.GlobalParamsAndUtils.LOCAL_DATE_FORMATTER;
import static pl.com.bottega.cms.infrastructure.GlobalParamsAndUtils.LOCAL_DATE_TIME_FORMATTER;
import static pl.com.bottega.cms.infrastructure.GlobalParamsAndUtils.STANDARD_LOCAL_DATE_TIME_FORMATTER;

/**
 * Created by ogurekk on 2017-04-09.
 */
public class ShowingsArranger implements Validatable {

    private LocalDate fromDate;
    private LocalDate untilDate;

    private List<DayOfWeek> weekDays;
    private List<LocalTime> hours;

    private List<Showing> showings;


    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getUntilDate() {
        return untilDate;
    }

    public void setUntilDate(LocalDate untilDate) {
        this.untilDate = untilDate;
    }

    public List<DayOfWeek> getWeekDays() {
        return weekDays;
    }

    public void setWeekDays(List<DayOfWeek> weekDays) {
        this.weekDays = weekDays;
    }

    public List<LocalTime> getHours() {
        return hours;
    }

    public void setHours(List<LocalTime> hours) {
        this.hours = hours;
    }

    public List<Showing> getShowings() {
        if (showings == null) {
            calculateShowings();
        }
        return showings;
    }

    private void calculateShowings() {
        showings = new LinkedList<>();
        LocalDate actualDate = fromDate;
        while (actualDate.compareTo(untilDate) <= 0) {
            DayOfWeek dayOfWeek = actualDate.getDayOfWeek();
            if (weekDays.contains(dayOfWeek)) {
                for (LocalTime hour : hours) {
                    Showing showing = new Showing();
                    showing.setBeginsAt(LocalDateTime.parse(actualDate + " " + hour, STANDARD_LOCAL_DATE_TIME_FORMATTER));
                    showings.add(showing);
                }
            }
            actualDate = actualDate.plusDays(1);
        }
    }

    @Override
    public void validate(ValidationErrors errors) {
        if (fromDate == null)
            errors.add("fromDate", "can't be blank");
        if (untilDate == null)
            errors.add("untilDate", "can't be blank");
        if (isEmpty(weekDays))
            errors.add("weekDays", "can't be blank");
        if (isEmpty(hours))
            errors.add("hours", "can't be blank");
    }


}
