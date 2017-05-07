package pl.com.bottega.cms.infrastructure.processes;

import pl.com.bottega.cms.model.commands.CollectPaymentCommand;
import pl.com.bottega.cms.model.reservation.ChargeResult;

/**
 * Created by ogurekk on 2017-05-07.
 */
public interface PaymentCollector {

    ChargeResult collectPayment(CollectPaymentCommand cmd);

}
