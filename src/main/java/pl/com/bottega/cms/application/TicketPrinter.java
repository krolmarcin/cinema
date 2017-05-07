package pl.com.bottega.cms.application;

import com.itextpdf.text.DocumentException;
import pl.com.bottega.cms.model.reservation.ReservationNumber;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by maciek on 06.05.2017.
 */
public interface TicketPrinter {

    byte[] printTicket(ReservationNumber reservationNumber) throws DocumentException, IOException;


}
