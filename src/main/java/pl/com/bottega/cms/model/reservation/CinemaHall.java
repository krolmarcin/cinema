package pl.com.bottega.cms.model.reservation;

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

    private boolean[][] seatConfiguration;

    private boolean[][] testSeatConfiguration;

    CinemaHall(boolean[][] seatConfiguration) {
        this.seatConfiguration = seatConfiguration;
    }

    private static boolean[][] initStandardSeatConfiguration() {
        return new boolean[10][15];
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
        seatConfiguration[detailedSeat.getRow()][detailedSeat.getSeat()] = true;
    }

    public void reserveSeats(Set<DetailedSeat> detailedSeats) {
        for (DetailedSeat detailedSeat : detailedSeats) {
            reserveSeat(detailedSeat);
        }
    }

    public void updateSeatConfiguration(List<Reservation> reservations) {
        for (Reservation reservation : reservations) {
            reserveSeats(reservation.getDetailedSeats());
        }
    }

    public void ensureReservationCompatible(Set<DetailedSeat> detailedSeats) {
        testSeatConfiguration = Arrays.copyOf(seatConfiguration, seatConfiguration.length);
        for (DetailedSeat detailedSeat : detailedSeats) {
            int row = detailedSeat.getRow();
            int seat = detailedSeat.getSeat();
            if (!(checkIfSeatExists(row, seat))) {
                throw new ReservationRequestException("No such seat exists.", row, seat);
            }
            if (!checkIfAvailable(row, seat)) {
                throw new ReservationRequestException("Seat has already been occupied.", row, seat);
            }
            testReserveSeat(row, seat);
        }

        for (DetailedSeat detailedSeat : detailedSeats) {
            int row = detailedSeat.getRow();
            int seat = detailedSeat.getSeat();
            if (!testForCompatiblity(row, seat)) {
                throw new ReservationRequestException("Cannot leave one free seat next to an occupied seat.");
            }
        }
        seatConfiguration = testSeatConfiguration;
    }

    public boolean checkIfSeatExists(int row, int seat) {
        return ((0 <= row && row < seatConfiguration.length) && (0 <= seat && seat < seatConfiguration[row].length));
    }

    private void testReserveSeat(int row, int seat) {
        testSeatConfiguration[row][seat] = true;
    }

    private boolean testForCompatiblity(int row, int seat) {
        boolean leftleft = seat <= 1 ? true : testSeatConfiguration[row][seat-2];
        boolean left = seat == 0 ? true : testSeatConfiguration[row][seat-1] ;
        boolean current = testSeatConfiguration[row][seat];
        boolean right = seat == testSeatConfiguration[row].length-1 ? true : testSeatConfiguration[row][seat+1];
        boolean rightright = seat >= testSeatConfiguration[row].length-2 ? true : testSeatConfiguration[row][seat+2];
        return (!((leftleft && !left && current) || (current && !right && rightright)));
    }

}
