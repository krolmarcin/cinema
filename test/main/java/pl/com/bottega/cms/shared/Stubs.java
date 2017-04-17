package pl.com.bottega.cms.shared;

import pl.com.bottega.cms.model.*;
import pl.com.bottega.cms.model.commands.CreateCinemaCommand;
import pl.com.bottega.cms.model.commands.CreateMovieCommand;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import pl.com.bottega.cms.model.commands.CreateCinemaCommand;
import pl.com.bottega.cms.model.commands.CreateShowingsCommand;

import static java.time.DayOfWeek.*;
import static pl.com.bottega.cms.infrastructure.GlobalParamsAndUtils.LOCAL_DATE_FORMATTER;
import static pl.com.bottega.cms.infrastructure.GlobalParamsAndUtils.LOCAL_DATE_TIME_FORMATTER;

/**
 * Created by ogurekk on 2017-04-09.
 */
public class Stubs {

    public static final Long TEST_LONG_0 = 0L;
    public static final Long TEST_LONG_1 = 1L;
    public static final Long TEST_LONG_2 = 2L;

    public static final int TEST_INT_0 = 0;
    public static final int TEST_INT_1 = 1;


    public static final String TEST_STRING_0 = "test string 0";
    public static final String TEST_STRING_1 = "test string 1";
    public static final String TEST_STRING_2 = "test string 2";
    public static final String TEST_STRING_3 = "test string 3";
    public static final String TEST_STRING_4 = "test string 4";
    public static final String TEST_STRING_5 = "test string 5";
    public static final String TEST_STRING_6 = "test string 6";
    public static final String TEST_STRING_7 = "test string 7";

    public static final Set<String> TEST_STRING_SET_0 = initTestStringSet0();
    public static final Set<String> TEST_STRING_SET_1 = initTestStringSet1();

    public static final LocalDateTime TEST_LOCAL_DATE_TIME_0 = LocalDateTime.parse("2015/05/06 12:30", LOCAL_DATE_TIME_FORMATTER);
    public static final LocalDateTime TEST_LOCAL_DATE_TIME_1 = LocalDateTime.parse("2015/06/01 16:31", LOCAL_DATE_TIME_FORMATTER);
    public static final LocalDateTime TEST_LOCAL_DATE_TIME_2 = LocalDateTime.parse("2014/07/02 13:32", LOCAL_DATE_TIME_FORMATTER);

    public static final LocalDate TEST_LOCAL_DATE_0 = LocalDate.parse("2017/04/09", LOCAL_DATE_FORMATTER);
    public static final LocalDate TEST_LOCAL_DATE_1 = LocalDate.parse("2017/04/20", LOCAL_DATE_FORMATTER);

    public static final LocalTime TEST_LOCAL_TIME_0 = LocalTime.parse("15:40");
    public static final LocalTime TEST_LOCAL_TIME_1 = LocalTime.parse("13:20");
    public static final LocalTime TEST_LOCAL_TIME_2 = LocalTime.parse("18:47");

    public static final List<LocalDateTime> TEST_LOCAL_DATE_TIME_LIST_0 = Arrays.asList(new LocalDateTime[] {TEST_LOCAL_DATE_TIME_0, TEST_LOCAL_DATE_TIME_1, TEST_LOCAL_DATE_TIME_2});

    public static final List<LocalTime> TEST_LOCAL_TIME_LIST_0 = Arrays.asList(new LocalTime[] {TEST_LOCAL_TIME_0, TEST_LOCAL_TIME_1, TEST_LOCAL_TIME_2});

    public static final List<String> TEST_DAY_OF_WEEK_LIST_0 = Arrays.asList(new String[] {SUNDAY.toString()});
    public static final List<String> TEST_DAY_OF_WEEK_LIST_1 = Arrays.asList(new String[] {TUESDAY.toString(), THURSDAY.toString()});

    public static final ShowingsArranger TEST_SHOWINGS_ARRANGER_0 = initShowingArranger();

    public static final CreateCinemaCommand TEST_CREATE_CINEMA_COMMAND_0 = initCreateCinemaCommand0();

    public static final Cinema TEST_CINEMA_0 = initTestCinema0();

    public static final CreateMovieCommand TEST_CREATE_MOVIE_COMMAND_0 = initCreateMovieCommand0();

    public static final Movie TEST_MOVIE_0 = initTestMovie0();

    public static final CreateShowingsCommand TEST_CREATE_SHOWING_COMMAND_0 = initCreateShowingCommand();

    public static final List<Showing> TEST_SHOWING_LIST_0 = initShowings0();

    public static final Showing TEST_SHOWING_0 = TEST_SHOWING_LIST_0.get(0);


    public static CreateCinemaCommand initCreateCinemaCommand0() {
        CreateCinemaCommand createCinemaCommand = new CreateCinemaCommand();
        createCinemaCommand.setName(TEST_STRING_0);
        createCinemaCommand.setCity(TEST_STRING_1);
        return createCinemaCommand;
    }

    public static Cinema initTestCinema0() {
        Cinema cinema = new Cinema(TEST_CREATE_CINEMA_COMMAND_0);
        cinema.setId(TEST_LONG_0);
        return cinema;
    }

    public static CreateMovieCommand initCreateMovieCommand0() {
        CreateMovieCommand createMovieCommand = new CreateMovieCommand();
        createMovieCommand.setTitle(TEST_STRING_0);
        createMovieCommand.setDescription(TEST_STRING_1);
        createMovieCommand.setActors(TEST_STRING_SET_0);
        createMovieCommand.setGenres(TEST_STRING_SET_1);
        createMovieCommand.setMinAge(TEST_INT_0);
        createMovieCommand.setLength(TEST_INT_1);
        return createMovieCommand;
    }

    public static Movie initTestMovie0() {
        Movie movie = new Movie(TEST_CREATE_MOVIE_COMMAND_0);
        movie.setId(TEST_LONG_0);
        return movie;
    }

    public static CreateShowingsCommand initCreateShowingCommand() {
        CreateShowingsCommand createShowingsCommand = new CreateShowingsCommand();
        createShowingsCommand.setDatesLocalDateTime(TEST_LOCAL_DATE_TIME_LIST_0);
        return createShowingsCommand;
    }

    public static List<Showing> initShowings0() {
        ShowingsFactory showingsFactory = new ShowingsFactory();
        List<Showing> showings = showingsFactory.createShowings(TEST_MOVIE_0, TEST_CINEMA_0, TEST_CREATE_SHOWING_COMMAND_0);
        showings.get(0).setId(TEST_LONG_0);
        showings.get(1).setId(TEST_LONG_1);
        showings.get(2).setId(TEST_LONG_2);
        return showings;
    }

    public static ShowingsArranger initShowingArranger() {
        ShowingsArranger showingsArranger = new ShowingsArranger();
        showingsArranger.setFromDate(TEST_LOCAL_DATE_TIME_0.toString());
        showingsArranger.setUntilDate(TEST_LOCAL_DATE_TIME_1.toString());
        showingsArranger.setHours(TEST_LOCAL_TIME_LIST_0);
        showingsArranger.setWeekDays(TEST_DAY_OF_WEEK_LIST_1);
        return showingsArranger;
    }

    public static Set<String> initTestStringSet0() {
        Set<String> stringSet = new HashSet<String>();
        stringSet.addAll(Arrays.asList(new String[] {TEST_STRING_2, TEST_STRING_3}));
        return stringSet;
    }

    public static Set<String> initTestStringSet1() {
        Set<String> stringSet = new HashSet<String>();
        stringSet.addAll(Arrays.asList(new String[] {TEST_STRING_4, TEST_STRING_5, TEST_STRING_6}));
        return stringSet;
    }

}
