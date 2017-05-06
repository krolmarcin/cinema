package pl.com.bottega.cms.model.reservation;

import pl.com.bottega.cms.infrastructure.validation.Validatable;
import pl.com.bottega.cms.model.reservation.DetailedSeat;
import pl.com.bottega.cms.model.reservation.Reservation;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * Created by ogurekk on 2017-04-22.
 */
public enum CinemaHall {

    STANDARD(initStandardSeatConfiguration());

    private static final int ROWS = 10;
    private static final int SEATS = 15;

    private boolean[][] seatConfiguration;

    CinemaHall(boolean[][] seatConfiguration) {
        this.seatConfiguration = seatConfiguration;
    }

    private static boolean[][] initStandardSeatConfiguration() {
        return new boolean[ROWS][SEATS];
    }

    public boolean[][] getSeatConfiguration() {
        return seatConfiguration;
    }

    public boolean checkIfAvailable(int row, int seat) {
        return !seatConfiguration[row][seat];
    }

    public void reserveSeat(int row, int seat) {
        seatConfiguration[row][seat] = true;
    }

    public void reserveSeat(DetailedSeat detailedSeat) {
        int row = detailedSeat.getRow();
        int seat = detailedSeat.getSeat();
        ensureSeatExists(row, seat);
        reserveSeat(row, seat);
    }

    public void reserveSeats(Set<DetailedSeat> detailedSeats) {
        for (DetailedSeat detailedSeat : detailedSeats) {
            reserveSeat(detailedSeat);
        }
    }

    public void updateSeatConfiguration(Set<Reservation> reservations) {
        for (Reservation reservation : reservations) {
            reserveSeats(reservation.getDetailedSeats());
        }
    }

    public void ensureReservationCompatible(Set<DetailedSeat> detailedSeats) {
        boolean [][] testSeatConfiguration = new boolean[seatConfiguration.length][];
        copySeatConfiguration(testSeatConfiguration);
        boolean canViolateOneSeatRule = validateForExistenceAndAvailability(detailedSeats, testSeatConfiguration);
        if (!canViolateOneSeatRule) {
            validateForOneSeatRuleCompliance(detailedSeats, testSeatConfiguration);
        }
        seatConfiguration = testSeatConfiguration;
    }

    public boolean checkIfSeatExists(int row, int seat) {
        return ((0 <= row && row < seatConfiguration.length) && (0 <= seat && seat < seatConfiguration[row].length));
    }

    private void copySeatConfiguration(boolean[][] testSeatConfiguration) {
        for (int i=0; i<testSeatConfiguration.length; i++) {
            boolean[] testSeatSubConfiguration = Arrays.copyOf(seatConfiguration[i], seatConfiguration[i].length);
            testSeatConfiguration[i] = testSeatSubConfiguration;
        }
    }

    private boolean validateForExistenceAndAvailability(Set<DetailedSeat> detailedSeats, boolean[][] testSeatConfiguration) {
        boolean canViolateOneSeatRule = false;
        for (DetailedSeat detailedSeat : detailedSeats) {
            int row = detailedSeat.getRow();
            int seat = detailedSeat.getSeat();
            ensureSeatExists(row, seat);
            canViolateOneSeatRule = canViolateOneSeatRule || canViolateOneSeatRule(testSeatConfiguration);
            ensureSeatAvailable(row, seat, testSeatConfiguration);
            testReserveSeat(row, seat, testSeatConfiguration);
        }
        return canViolateOneSeatRule;
    }

    private void ensureSeatExists(int row, int seat) {
        if (!(checkIfSeatExists(row, seat))) {
            throw new ReservationRequestOutOfBoundsException("No such seat exists.", row, seat);
        }
    }

    private void ensureSeatAvailable(int row, int seat, boolean[][] testSeatConfiguration) {
        if (!testCheckIfAvailable(row, seat, testSeatConfiguration)) {
            throw new ReservationRequestException("Seat has already been occupied.", row, seat);
        }
    }

    private boolean testCheckIfAvailable(int row, int seat, boolean[][] testSeatConfiguration) {
        return !testSeatConfiguration[row][seat];
    }

    private void testReserveSeat(int row, int seat, boolean[][] testSeatConfiguration) {
        testSeatConfiguration[row][seat] = true;
    }

    private void validateForOneSeatRuleCompliance(Set<DetailedSeat> detailedSeats, boolean[][] testSeatConfiguration) {
        for (DetailedSeat detailedSeat : detailedSeats) {
            int row = detailedSeat.getRow();
            int seat = detailedSeat.getSeat();
            if (!testForCompatiblity(row, seat, testSeatConfiguration)) {
                throw new ReservationRequestException("Cannot leave one free seat next to an occupied seat.");
            }
        }
    }

    private boolean testForCompatiblity(int row, int seat, boolean[][] testSeatConfiguration) {
        boolean leftleft = seat <= 1 ? true : testSeatConfiguration[row][seat-2];
        boolean left = seat == 0 ? true : testSeatConfiguration[row][seat-1] ;
        boolean current = testSeatConfiguration[row][seat];
        boolean right = seat == testSeatConfiguration[row].length-1 ? true : testSeatConfiguration[row][seat+1];
        boolean rightright = seat >= testSeatConfiguration[row].length-2 ? true : testSeatConfiguration[row][seat+2];
        return (!((leftleft && !left && current) || (current && !right && rightright)));
    }

    private boolean canViolateOneSeatRule(boolean[][] testSeatConfiguration) {
        for (int i=0; i<testSeatConfiguration.length; i++) {
            for (int j=0; j<testSeatConfiguration[i].length-2; j++) {
                boolean leftleft = testSeatConfiguration[i][j];
                boolean left = testSeatConfiguration[i][j+1];
                boolean current = testSeatConfiguration[i][j+2];
                if (!(leftleft || left || current)) {
                    return false;
                }
            }
        }
        return true;
    }

}
