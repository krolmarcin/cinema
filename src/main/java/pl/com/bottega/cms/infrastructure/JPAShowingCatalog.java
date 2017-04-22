package pl.com.bottega.cms.infrastructure;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by ogurekk on 2017-04-22.
 */
public class JPAShowingCatalog {

    @PersistenceContext
    private EntityManager entityManager;
}
