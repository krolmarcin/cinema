package pl.com.bottega.cms.infrastructure.repositories;

import pl.com.bottega.cms.model.repositories.ShowingRepository;
import pl.com.bottega.cms.model.reservation.Reservation;
import pl.com.bottega.cms.model.showing.Showing;
import pl.com.bottega.cms.ui.InvalidActionException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by maciek on 09.04.2017.
 */
public class JPAShowingRepository implements ShowingRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void put(Showing s) {
        if (s.getId() != null) {
            Showing showing = entityManager.find(Showing.class, s.getId());
            if (showing == null) {
                entityManager.persist(s);
            } else {
                entityManager.merge(s);
            }
        } else {
            entityManager.persist(s);
        }


    }

    @Override
    public Showing get(Long id) {
        Showing showing = entityManager.find(Showing.class, id);
        if (showing == null) {
            throw new EntityNotFoundException("Showing", id);
        }
        return showing;
    }

    @Override
    public Set<Reservation> getReservations(Long showingId) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Reservation> criteriaQuery = criteriaBuilder.createQuery(Reservation.class);
        Root<Reservation> root = criteriaQuery.from(Reservation.class);
        criteriaQuery.where(criteriaBuilder.equal(root.get("showing"), showingId));
        TypedQuery<Reservation> query = entityManager.createQuery(criteriaQuery);
        List<Reservation> reservations = query.getResultList();
        return new HashSet<Reservation>(reservations);
    }

}
