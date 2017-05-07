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

    private String errorMessage;

    private LocalDateTime date;


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

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
