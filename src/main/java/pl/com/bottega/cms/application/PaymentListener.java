package pl.com.bottega.cms.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;
import pl.com.bottega.cms.infrastructure.tickets.TicketMailer;
import pl.com.bottega.cms.model.events.TransactionByCCSuccessfullEvent;
import pl.com.bottega.cms.model.repositories.ReservationRepository;
import pl.com.bottega.cms.model.reservation.Reservation;
import pl.com.bottega.cms.model.reservation.ReservationNumber;

/**
 * Created by ogurekk on 2017-05-07.
 */
@Component
public class PaymentListener {

    @Autowired
    private TicketMailer ticketMailer;

    @TransactionalEventListener
    @Async
    public void reservationPaidByCC(TransactionByCCSuccessfullEvent event) {
        ticketMailer.sendTickets(event.getReservationNumber());

    }
}
