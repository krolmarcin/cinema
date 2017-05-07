package pl.com.bottega.cms.application.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.cms.infrastructure.processes.PaymentCollector;
import pl.com.bottega.cms.model.commands.CollectPaymentCommand;
import pl.com.bottega.cms.model.repositories.TransactionRepository;
import pl.com.bottega.cms.model.transactions.Transaction;

/**
 * Created by ogurekk on 2017-05-07.
 */
@Transactional
public class StandardPaymentCollector implements PaymentCollector{

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public void collectPayment(CollectPaymentCommand cmd) {
        Transaction transaction = new Transaction();
        transaction.setCashierId(cmd.getCashierId());
        transaction.setCreditCard(cmd.getCreditCard());
        transaction.setPaymentType(cmd.getType());
        transactionRepository.put(transaction);
    }
}
