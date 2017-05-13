package pl.com.bottega.cms.application.implementation;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.cms.infrastructure.processes.PaymentCollector;
import pl.com.bottega.cms.model.commands.CalculatePriceCommand;
import pl.com.bottega.cms.model.commands.CollectPaymentCommand;
import pl.com.bottega.cms.model.events.TransactionByCCSuccessfullEvent;
import pl.com.bottega.cms.model.repositories.ReservationRepository;
import pl.com.bottega.cms.model.repositories.TransactionRepository;
import pl.com.bottega.cms.model.reservation.PaymentFacade;
import pl.com.bottega.cms.model.reservation.PriceCalculator;
import pl.com.bottega.cms.model.reservation.Reservation;
import pl.com.bottega.cms.model.transactions.Transaction;
import pl.com.bottega.cms.ui.InvalidActionException;

import java.math.BigDecimal;

import static pl.com.bottega.cms.model.transactions.PaymentType.CREDIT_CARD;

@Transactional(noRollbackFor = RuntimeException.class)
public class StandardPaymentCollector implements PaymentCollector {


    private ApplicationEventPublisher applicationEventPublisher;
    private TransactionRepository transactionRepository;
    private ReservationRepository reservationRepository;
    private PaymentFacade paymentFacade;
    private PriceCalculator priceCalculator;

    public StandardPaymentCollector(ApplicationEventPublisher applicationEventPublisher,
                                    TransactionRepository transactionRepository,
                                    ReservationRepository reservationRepository,
                                    PaymentFacade paymentFacade,
                                    PriceCalculator priceCalculator) {
        this.applicationEventPublisher = applicationEventPublisher;
        this.transactionRepository = transactionRepository;
        this.reservationRepository = reservationRepository;
        this.paymentFacade = paymentFacade;
        this.priceCalculator = priceCalculator;
    }

    @Override
    public void collectPayment(CollectPaymentCommand cmd) {
        Reservation reservation = getExistingReservation(cmd);
        reservation.setPaymentFacade(paymentFacade);
        cmd.setTotalPriceForTransaction(getTotalPrice(reservation));
        Transaction transaction = reservation.collectPayment(cmd);
        transactionRepository.put(transaction);
        isSuccessful(transaction);

        if (cmd.getType().equals(CREDIT_CARD)) {
            applicationEventPublisher.publishEvent(new TransactionByCCSuccessfullEvent(cmd.getReservationNumber()));
        }
    }

    private BigDecimal getTotalPrice(Reservation reservation) {
        CalculatePriceCommand cmd = new CalculatePriceCommand();
        cmd.setShowId(reservation.getShowing().getId());
        cmd.setTickets(reservation.getReservationItems());
        return priceCalculator.calculatePrices(cmd).getTotalPrice();
    }

    private void isSuccessful(Transaction transaction) {
        if (transaction.getChargeResult() != null) {
            String errorMessage = transaction.getChargeResult().getErrorMessage();
            if (errorMessage != null)
                throw new InvalidActionException(errorMessage);
        }
    }

    private Reservation getExistingReservation(CollectPaymentCommand cmd) {
        Reservation reservation = reservationRepository.get(cmd.getReservationNumber());
        isReservationExist(reservation);
        return reservation;
    }

    private void isReservationExist(Reservation reservation) {
        if (reservation == null)
            throw new InvalidActionException("Reservation does not exists");
    }

}
