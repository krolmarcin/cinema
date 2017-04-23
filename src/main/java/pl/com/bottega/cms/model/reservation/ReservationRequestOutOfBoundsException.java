package pl.com.bottega.cms.model.reservation;

/**
 * Created by ogurekk on 2017-04-23.
 */
public class ReservationRequestOutOfBoundsException extends RuntimeException {
    public ReservationRequestOutOfBoundsException(String msg, int row, int seat) {
        super(String.format("%s row '%s', seat '%s'",msg, row, seat));
    }
}
