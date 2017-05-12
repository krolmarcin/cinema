package pl.com.bottega.cms.model.transactions;

import pl.com.bottega.cms.model.reservation.ChargeResult;
import pl.com.bottega.cms.model.reservation.Reservation;
import pl.com.bottega.cms.model.reservation.ReservationStatus;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Transaction {

    @Id
    @GeneratedValue
    private Integer id;

    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    private Integer cashierId;

    @Embedded
    private ChargeResult chargeResult;

    private LocalDateTime date;

    public Transaction() {
    }

    public Transaction(PaymentType paymentType, Integer cashierId, ChargeResult chargeResult) {
        this.paymentType = paymentType;
        this.cashierId = cashierId;
        this.chargeResult = chargeResult;
        this.date = LocalDateTime.now();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public Integer getCashierId() {
        return cashierId;
    }

    public void setCashierId(Integer cashierId) {
        this.cashierId = cashierId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public ChargeResult getChargeResult() {
        return chargeResult;
    }

}
