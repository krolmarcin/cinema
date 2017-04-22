package pl.com.bottega.cms.model;

import java.util.List;
import java.util.Set;

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
        return new boolean[10][15];
    }

    public boolean[][] getSeatConfiguration() {
        return seatConfiguration;
    }

    public void reserveSeat(DetailedSeat detailedSeat) {
        seatConfiguration[detailedSeat.getRow()][detailedSeat.getSeat()] = true;
    }

    public void reserveSeats(Set<DetailedSeat> detailedSeats) {
        for (DetailedSeat detailedSeat : detailedSeats) {
            reserveSeat(detailedSeat);
        }
    }

    public void updateReservations(List<Reservation> reservations) {
        for (Reservation reservation : reservations) {
            reserveSeats(reservation.getDetailedSeats());
        }
    }
}
