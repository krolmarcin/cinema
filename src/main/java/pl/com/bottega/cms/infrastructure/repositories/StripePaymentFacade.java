package pl.com.bottega.cms.infrastructure.repositories;

import com.stripe.Stripe;
import com.stripe.exception.*;
import com.stripe.model.Charge;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.cms.model.reservation.ChargeResult;
import pl.com.bottega.cms.model.reservation.PaymentFacade;
import pl.com.bottega.cms.model.reservation.ReservationStatus;
import pl.com.bottega.cms.model.transactions.CreditCard;
import pl.com.bottega.cms.ui.InvalidActionException;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

@Service
public class StripePaymentFacade implements PaymentFacade {

    private final String apiKey = "sk_test_KlGJM1dMRLzqL8r9kiubjib6";

    @Override
    public ChargeResult charge(CreditCard cc, BigDecimal amount) {

        Stripe.apiKey = apiKey;

        Map<String, Object> source = new HashMap<>();
        source.put("exp_month", cc.getExpirationMonth());
        source.put("exp_year", cc.getExpirationYear());
        source.put("number", cc.getNumber());
        source.put("cvc", cc.getCvc());
        source.put("object", "card");

        Map<String, Object> chargeParams = new HashMap<>();
        chargeParams.put("amount", getAmountInCents(amount));
        chargeParams.put("currency", "pln");
        chargeParams.put("source", source);

        ChargeResult chargeResult;
        try {
            Charge charge = Charge.create(chargeParams);
            chargeResult = new ChargeResult(charge.getStatus().toUpperCase(), charge.getId(), null);
        } catch (AuthenticationException e) {
            throw new InvalidActionException("AuthenticationException");
        } catch (InvalidRequestException e) {
            throw new InvalidActionException("InvalidRequestException");
        } catch (APIConnectionException e) {
            throw new InvalidActionException("APIConnectionException");
        } catch (CardException e) {
            String errorMessage = "CardException:" + " " + e.getCode() + ": " + e.getMessage();
            chargeResult = new ChargeResult("FAILED", e.getCharge(), errorMessage);
        } catch (APIException e) {
            throw new InvalidActionException("APIException");
        }
        return chargeResult;
    }

    private BigInteger getAmountInCents(BigDecimal amount) {
        return amount.multiply(new BigDecimal(100)).toBigInteger();
    }

}
