package pl.com.bottega.cms.model.reservation;

import pl.com.bottega.cms.model.commands.CommandInvalidException;

/**
 * Created by ogurekk on 2017-04-23.
 */
public class ReservationRequestException extends RuntimeException {
    public ReservationRequestException(String msg, int row, int seat) {
        super(String.format("%s row '%s', seat '%s'",msg, row, seat));
    }
    public ReservationRequestException(String msg) {
        super(msg);
    }
}
