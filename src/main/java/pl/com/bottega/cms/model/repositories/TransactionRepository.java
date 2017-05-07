package pl.com.bottega.cms.model.repositories;

import pl.com.bottega.cms.model.transactions.Transaction;

/**
 * Created by ogurekk on 2017-05-07.
 */
public interface TransactionRepository {
    void put(Transaction t);
}
