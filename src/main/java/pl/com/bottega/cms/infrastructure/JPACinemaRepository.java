package pl.com.bottega.cms.infrastructure;

import pl.com.bottega.cms.model.Cinema;
import pl.com.bottega.cms.model.EntityNotFoundException;
import pl.com.bottega.cms.model.CinemaRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

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
            throw new EntityNotFoundException(String.format("Cinema id: '%s' does not exist", id));
        }
        return cinema;
    }

    @Override
    public boolean exists(String name, String city) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Cinema> criteriaQuery = criteriaBuilder.createQuery(Cinema.class);
        Root<Cinema> root = criteriaQuery.from(Cinema.class);
        criteriaQuery.where(criteriaBuilder.equal(root.get("name"), name), criteriaBuilder.equal(root.get("city"), city));
        TypedQuery<Cinema> query = entityManager.createQuery(criteriaQuery);
        return !query.getResultList().isEmpty();
    }

}
