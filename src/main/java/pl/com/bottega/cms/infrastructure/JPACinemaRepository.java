package pl.com.bottega.cms.infrastructure;

import pl.com.bottega.cms.model.Cinema;
import pl.com.bottega.cms.model.CinemaNotFoundException;
import pl.com.bottega.cms.model.CinemaRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by maciek on 09.04.2017.
 */
public class JPACinemaRepository implements CinemaRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void put(Cinema c) {
        entityManager.persist(c);
    }

    @Override
    public Cinema get(Long id) {
        Cinema cinema = entityManager.find(Cinema.class, id);
        if (cinema == null) {
            throw new CinemaNotFoundException(String.format("Cinema id %s not exist", id));
        }
        return cinema;
    }

    @Override
    public boolean exists(String name, String city) {
        return false;
    }

}
