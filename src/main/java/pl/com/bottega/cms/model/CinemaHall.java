package pl.com.bottega.cms.model;

/**
 * Created by ogurekk on 2017-04-22.
 */
public enum CinemaHall {

    STANDARD(initStandardSeatConfiguration());

    private boolean[][] seatConfiguration;

    CinemaHall(boolean[][] seatConfiguration) {
        this.seatConfiguration = seatConfiguration;
    }

    private static boolean[][] initStandardSeatConfiguration() {
        return new boolean[][]{};
    }
}
