package pl.com.bottega.cms.model.transactions;

import pl.com.bottega.cms.model.reservation.ReservationStatus;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by ogurekk on 2017-05-07.
 */
@Entity
public class Transaction {

    @Id
    @GeneratedValue
    private Integer id;

    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    private Integer cashierId;

    @OneToOne(cascade = CascadeType.ALL)
    private CreditCard creditCard;

    private LocalDateTime date;
    private Integer reservationId;

    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus;

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

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Integer getReservationId() {
        return reservationId;
    }

    public void setReservationId(Integer reservationId) {
        this.reservationId = reservationId;
    }
}
