package pl.com.bottega.cms.infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.cms.application.TicketPrinter;
import pl.com.bottega.cms.application.queries.ReservationQuery;
import pl.com.bottega.cms.model.repositories.ReservationRepository;
import pl.com.bottega.cms.model.reservation.Reservation;
import pl.com.bottega.cms.model.reservation.ReservationNumber;

/**
 * Created by maciek on 06.05.2017.
 */
public class ITextTicketPrinter implements TicketPrinter {


    private ReservationRepository reservationRepository;

    public ITextTicketPrinter(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public byte[] printTicket(ReservationNumber reservationNumber) {
        Reservation reservation = reservationRepository.get(reservationNumber);

        return new byte[0];
    }
}
