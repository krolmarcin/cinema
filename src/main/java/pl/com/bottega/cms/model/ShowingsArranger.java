package pl.com.bottega.cms.model;

import pl.com.bottega.cms.model.commands.Validatable;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

import static pl.com.bottega.cms.infrastructure.GlobalParamsAndUtils.*;

/**
 * Created by ogurekk on 2017-04-09.
 */
public class ShowingsArranger {

    private LocalDateTime fromDate;
    private LocalDateTime untilDate;

    private List<DayOfWeek> weekDays;
    private List<LocalTime> hours;

    private List<LocalDateTime> dates;


    public LocalDateTime getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = parseStringToLocalDateTime(fromDate);
    }

    public LocalDateTime getUntilDate() {
        return untilDate;
    }

    public void setUntilDate(String untilDate) {
        this.untilDate = parseStringToLocalDateTime(untilDate);
    }

    public List<DayOfWeek> getWeekDays() {
        return weekDays;
    }

    public void setWeekDays(List<String> weekDays) {
        List<DayOfWeek> weekDaysEnumList = new LinkedList<>();
        for (String s : weekDays) {
            weekDaysEnumList.add(DayOfWeek.valueOf(s.toUpperCase()));
        }
        this.weekDays = weekDaysEnumList;
    }

    public List<LocalTime> getHours() {
        return hours;
    }

    public void setHours(List<LocalTime> hours) {
        this.hours = hours;
    }

    public List<LocalDateTime> getDates() {
        if (dates == null) {
            calculateDates();
        }
        return dates;
    }

    private void calculateDates() {
        dates = new LinkedList<>();
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
    }


}
