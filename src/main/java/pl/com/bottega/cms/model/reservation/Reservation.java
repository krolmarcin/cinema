package pl.com.bottega.cms.model.reservation;

import pl.com.bottega.cms.model.commands.CalculatePriceCommand;
import pl.com.bottega.cms.model.commands.CollectPaymentCommand;
import pl.com.bottega.cms.model.showing.Showing;
import pl.com.bottega.cms.model.transactions.PaymentType;
import pl.com.bottega.cms.model.transactions.Transaction;
import pl.com.bottega.cms.ui.InvalidActionException;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static pl.com.bottega.cms.model.transactions.PaymentType.CASH;
import static pl.com.bottega.cms.model.transactions.PaymentType.CREDIT_CARD;

@Entity
public class Reservation {

    @Id
    @GeneratedValue
    private Long id;

    @Embedded
    @AttributeOverride(name = "number", column = @Column(name = "reservation_number"))
    private ReservationNumber reservationNumber;

    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "reservation_id")
    private Set<ReservationItem> reservationItems;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "reservation_id")
    private Set<DetailedSeat> detailedSeats;

    @ManyToOne
    @JoinColumn(name = "showing_id")
    private Showing showing;

    @Embedded
    private Customer customer;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "reservation_id")
    private Set<Transaction> transactions;

    @Transient
    private PaymentFacade paymentFacade;

    public Reservation() {
    }

    public Transaction collectPayment(CollectPaymentCommand cmd) {
        checkStatus();
        if (cmd.getType().equals(PaymentType.CREDIT_CARD)) {
            return payByCreditCard(cmd);
        } else {
            return payByCash(cmd);
        }
    }

    private void checkStatus() {
        if (!(reservationStatus.equals(ReservationStatus.PENDING) || (reservationStatus.equals(ReservationStatus.PAYMENT_FAILED))))
            throw new InvalidActionException("Reservation must be in PENDING or PAYMENT_FAILED status");
    }

    private Transaction payByCash(CollectPaymentCommand cmd) {
        this.reservationStatus = ReservationStatus.PAID;
        return new Transaction(CASH, cmd.getCashierId(), null);
    }

    private Transaction payByCreditCard(CollectPaymentCommand cmd) {
        ChargeResult chargeResult = paymentFacade.charge(cmd.getCreditCard(), cmd.getTotalPriceForTransaction());
        if (chargeResult.getStatus().equals("SUCCEEDED")) {
            this.reservationStatus = ReservationStatus.PAID;
        } else {
            this.reservationStatus = ReservationStatus.PAYMENT_FAILED;
        }
        return new Transaction(CREDIT_CARD, null, chargeResult);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ReservationNumber getReservationNumber() {
        return reservationNumber;
    }

    public void setReservationNumber(ReservationNumber reservationNumber) {
        this.reservationNumber = reservationNumber;
    }

    public ReservationStatus getReservationStatus() {
        return reservationStatus;
    }

    public void setReservationStatus(ReservationStatus reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

    public Set<ReservationItem> getReservationItems() {
        return reservationItems;
    }

    public void setReservationItems(Set<ReservationItem> reservationItems) {
        this.reservationItems = reservationItems;
    }

    public Set<DetailedSeat> getDetailedSeats() {
        return detailedSeats;
    }

    public void setDetailedSeats(Set<DetailedSeat> detailedSeats) {
        this.detailedSeats = detailedSeats;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Showing getShowing() {
        return showing;
    }

    public void setShowing(Showing showing) {
        this.showing = showing;
    }

    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void setPaymentFacade(PaymentFacade paymentFacade) {
        this.paymentFacade = paymentFacade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (this == null || o == null) {
            return false;
        }
        if (!(o instanceof Reservation)) {
            return false;
        }
        Reservation reservation = (Reservation) o;
        return this.reservationNumber.equals(reservation.reservationNumber);
    }

}
