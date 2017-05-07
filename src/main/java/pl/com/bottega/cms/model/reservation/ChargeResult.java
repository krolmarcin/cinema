package pl.com.bottega.cms.model.reservation;

public class ChargeResult {

    private ReservationStatus reservationStatus;

    public ChargeResult(ReservationStatus reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

    public ReservationStatus getReservationStatus() {
        return reservationStatus;
    }

}
