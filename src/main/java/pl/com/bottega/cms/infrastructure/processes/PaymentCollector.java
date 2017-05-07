package pl.com.bottega.cms.infrastructure.processes;

import pl.com.bottega.cms.model.commands.CollectPaymentCommand;

/**
 * Created by ogurekk on 2017-05-07.
 */
public interface PaymentCollector {

    void collectPayment(CollectPaymentCommand cmd);
}
