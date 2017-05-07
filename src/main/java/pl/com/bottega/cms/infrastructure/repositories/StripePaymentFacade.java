package pl.com.bottega.cms.infrastructure.repositories;

import pl.com.bottega.cms.model.reservation.ChargeResult;
import pl.com.bottega.cms.model.reservation.PaymentFacade;
import pl.com.bottega.cms.model.transactions.CreditCard;

import java.math.BigDecimal;

public class StripePaymentFacade implements PaymentFacade {

    private final String apikey = "sk_test_KlGJM1dMRLzqL8r9kiubjib6";

    @Override
    public ChargeResult charge(CreditCard cc, BigDecimal amount) {
        return null;
    }

}
