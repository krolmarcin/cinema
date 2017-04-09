package pl.com.bottega.cms.shared;

import pl.com.bottega.cms.model.Cinema;
import pl.com.bottega.cms.model.CreateCinemaCommand;

/**
 * Created by ogurekk on 2017-04-09.
 */
public class Stubs {

    public static final Long TEST_ID_0 = 0L;
    public static final Long TEST_ID_1 = 1L;
    public static final Long TEST_ID_2 = 2L;

    public static final String TEST_NAME_0 = "test name 0";
    public static final String TEST_NAME_1 = "test name 1";
    public static final String TEST_NAME_2 = "test name 2";

    public static final Cinema TEST_CINEMA_0 = initTestCinema0();

    public static Cinema initTestCinema0() {
        CreateCinemaCommand createCinemaCommand = new CreateCinemaCommand();
        createCinemaCommand.setName(TEST_NAME_0);
        createCinemaCommand.setCity(TEST_NAME_1);
        Cinema cinema = new Cinema(createCinemaCommand);
        cinema.setId(TEST_ID_0);
        return cinema;
    }

}
