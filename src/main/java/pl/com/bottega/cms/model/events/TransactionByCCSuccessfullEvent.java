package pl.com.bottega.cms.model.events;

import pl.com.bottega.cms.model.reservation.ReservationNumber;

/**
 * Created by ogurekk on 2017-05-07.
 */
public class TransactionByCCSuccessfullEvent {

    private ReservationNumber reservationNumber;

    public TransactionByCCSuccessfullEvent(ReservationNumber reservationNumber) {
        this.reservationNumber = reservationNumber;
    }

    public ReservationNumber getReservationNumber() {
        return reservationNumber;
    }
}
