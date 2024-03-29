package pl.com.bottega.cms.model.commands;

import pl.com.bottega.cms.infrastructure.validation.Validatable;
import pl.com.bottega.cms.infrastructure.validation.ValidationError;
import pl.com.bottega.cms.model.reservation.ReservationNumber;
import pl.com.bottega.cms.model.transactions.CreditCard;
import pl.com.bottega.cms.model.transactions.PaymentType;

import java.math.BigDecimal;
import java.util.Arrays;

public class CollectPaymentCommand implements Validatable {

    private PaymentType type;
    private CreditCard creditCard;
    private Integer cashierId;
    private ReservationNumber reservationNumber;
    private BigDecimal totalPriceForTransaction;

    public PaymentType getType() {
        return type;
    }

    public void setType(String type) {
        this.type = PaymentType.valueOf(type.toUpperCase());
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    public Integer getCashierId() {
        return cashierId;
    }

    public void setCashierId(Integer cashierId) {
        this.cashierId = cashierId;
    }

    public ReservationNumber getReservationNumber() {
        return reservationNumber;
    }

    public void setReservationNumber(ReservationNumber reservationNumber) {
        this.reservationNumber = reservationNumber;
    }

    public BigDecimal getTotalPriceForTransaction() {
        return totalPriceForTransaction;
    }

    public void setTotalPriceForTransaction(BigDecimal totalPriceForTransaction) {
        this.totalPriceForTransaction = totalPriceForTransaction;
    }

    @Override
    public void validate(ValidationErrors errors) {
        ensureNotEmpty(type, "type", errors);
        if (!errors.getErrors().containsKey("type")) {
            switch (type) {
                case CREDIT_CARD:
                    ensureNotEmpty(creditCard, "creditCard", errors);
                    if (!errors.getErrors().containsKey("creditCard")) {
                        ensureNotEmpty(creditCard.getNumber(), "creditCard.number", errors);
                        ensureNotEmpty(creditCard.getExpirationMonth(), "creditCard.expirationMonth", errors);
                        ensureNotEmpty(creditCard.getExpirationYear(), "creditCard.expirationYear", errors);
                        ensureNotEmpty(creditCard.getCvc(), "creditCard.cvc", errors);
                    }
                    break;
                case CASH:
                    ensureNotEmpty(cashierId, "cashierId", errors);
                    break;
                default:
                    errors.add("type", ValidationError.INVALID.getValMsg() + Arrays.asList(PaymentType.values()));
            }
        }
    }

}
