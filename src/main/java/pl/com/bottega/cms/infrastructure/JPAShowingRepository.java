package pl.com.bottega.cms.infrastructure;

import pl.com.bottega.cms.model.Showing;
import pl.com.bottega.cms.model.ShowingRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by maciek on 09.04.2017.
 */
public class JPAShowingRepository implements ShowingRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void put(Showing s) {
        entityManager.persist(s);
    }
}
