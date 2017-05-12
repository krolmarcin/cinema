package pl.com.bottega.cms.infrastructure.processes;

import com.stripe.exception.*;
import pl.com.bottega.cms.model.commands.CollectPaymentCommand;
import pl.com.bottega.cms.model.reservation.ChargeResult;

/**
 * Created by ogurekk on 2017-05-07.
 */
public interface PaymentCollector {

    void collectPayment(CollectPaymentCommand cmd);

}
