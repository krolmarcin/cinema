package pl.com.bottega.cms.shared;

import pl.com.bottega.cms.model.Cinema;
import pl.com.bottega.cms.model.CreateCinemaCommand;
import pl.com.bottega.cms.model.CreateMovieCommand;
import pl.com.bottega.cms.model.Movie;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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
    public static final String TEST_STRING_5 = "test string 4";
    public static final String TEST_STRING_6 = "test string 4";
    public static final String TEST_STRING_7 = "test string 4";

    public static final Set<String> TEST_STRING_SET_0 = initTestStringSet0();
    public static final Set<String> TEST_STRING_SET_1 = initTestStringSet1();

    public static final Cinema TEST_CINEMA_0 = initTestCinema0();

    public static final Movie TEST_MOVIE_0 = initTestMovie0();

    public static Cinema initTestCinema0() {
        CreateCinemaCommand createCinemaCommand = new CreateCinemaCommand();
        createCinemaCommand.setName(TEST_STRING_0);
        createCinemaCommand.setCity(TEST_STRING_1);
        Cinema cinema = new Cinema(createCinemaCommand);
        cinema.setId(TEST_LONG_0);
        return cinema;
    }

    public static Movie initTestMovie0() {
        CreateMovieCommand createMovieCommand = new CreateMovieCommand();
        createMovieCommand.setTitle(TEST_STRING_0);
        createMovieCommand.setDescription(TEST_STRING_1);
        createMovieCommand.setActors(TEST_STRING_SET_0);
        createMovieCommand.setGenres(TEST_STRING_SET_1);
        createMovieCommand.setMinAge(TEST_INT_0);
        createMovieCommand.setLength(TEST_INT_1);
        Movie movie = new Movie(createMovieCommand);
        movie.setId(TEST_LONG_0);
        return movie;
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
