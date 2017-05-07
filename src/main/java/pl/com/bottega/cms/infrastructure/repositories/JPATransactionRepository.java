package pl.com.bottega.cms.infrastructure.repositories;

import pl.com.bottega.cms.model.repositories.TransactionRepository;
import pl.com.bottega.cms.model.transactions.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by ogurekk on 2017-05-07.
 */
public class JPATransactionRepository implements TransactionRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void put(Transaction t) {
        entityManager.persist(t);
    }
}
