package pl.com.bottega.cms.model.reservation;

import com.stripe.exception.*;
import pl.com.bottega.cms.model.transactions.CreditCard;

import java.math.BigDecimal;

public interface PaymentFacade {

    ChargeResult charge(CreditCard cc, BigDecimal amount);

}
