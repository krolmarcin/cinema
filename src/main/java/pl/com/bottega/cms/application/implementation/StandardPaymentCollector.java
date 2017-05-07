package pl.com.bottega.cms.application.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.cms.infrastructure.processes.PaymentCollector;
import pl.com.bottega.cms.model.commands.CollectPaymentCommand;
import pl.com.bottega.cms.model.events.TransactionByCCSuccessfullEvent;
import pl.com.bottega.cms.model.repositories.ReservationRepository;
import pl.com.bottega.cms.model.repositories.TransactionRepository;
import pl.com.bottega.cms.model.reservation.ChargeResult;
import pl.com.bottega.cms.model.reservation.Reservation;
import pl.com.bottega.cms.model.reservation.ReservationStatus;
import pl.com.bottega.cms.model.transactions.Transaction;
import pl.com.bottega.cms.ui.InvalidActionException;

/**
 * Created by ogurekk on 2017-05-07.
 */
@Transactional
public class StandardPaymentCollector implements PaymentCollector {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    public ChargeResult collectPayment(CollectPaymentCommand cmd) {
        Reservation reservation = reservationRepository.get(cmd.getReservationNumber());
        if (reservation == null)
            throw new InvalidActionException("There is no reservation");
        if (reservation.getReservationStatus().equals(ReservationStatus.PAID) || reservation.getReservationStatus().equals(ReservationStatus.CANCELLED))
            throw new InvalidActionException("Reservation was paid or cancelled");

        Transaction transaction = new Transaction();
        transaction.setCashierId(cmd.getCashierId());
        transaction.setErrorMessage("put error message here"); //todo: error message z paymentu
        transaction.setPaymentType(cmd.getType());


        transactionRepository.put(transaction);
        applicationEventPublisher.publishEvent(new TransactionByCCSuccessfullEvent(cmd.getReservationNumber()));
        return new ChargeResult(reservation.getReservationStatus());
    }

}
