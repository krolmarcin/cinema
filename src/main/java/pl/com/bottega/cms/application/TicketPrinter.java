package pl.com.bottega.cms.application;

import pl.com.bottega.cms.model.reservation.ReservationNumber;

/**
 * Created by maciek on 06.05.2017.
 */
public interface TicketPrinter {

    byte[] printTicket(ReservationNumber reservationNumber);


}
