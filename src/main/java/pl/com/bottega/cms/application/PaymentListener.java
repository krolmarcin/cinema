package pl.com.bottega.cms.application;

import org.springframework.scheduling.annotation.Async;
import pl.com.bottega.cms.model.reservation.ReservationNumber;

/**
 * Created by ogurekk on 2017-05-07.
 */
public class PaymentListener {

    @Async()
    public void reservationPaidByCC(ReservationNumber reservationNumber) {

    }
}
