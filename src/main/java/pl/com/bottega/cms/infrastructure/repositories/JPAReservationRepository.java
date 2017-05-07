package pl.com.bottega.cms.infrastructure.repositories;

import pl.com.bottega.cms.model.repositories.ReservationRepository;
import pl.com.bottega.cms.model.reservation.Reservation;
import pl.com.bottega.cms.model.reservation.ReservationNumber;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by maciek on 06.05.2017.
 */
public class JPAReservationRepository implements ReservationRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Reservation get(ReservationNumber number) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Reservation> criteriaQuery = criteriaBuilder.createQuery(Reservation.class);
        Root<Reservation> root = criteriaQuery.from(Reservation.class);
        criteriaQuery.where(criteriaBuilder.equal(root.get("reservationNumber"), number));
        TypedQuery<Reservation> query = entityManager.createQuery(criteriaQuery);
        List results = query.getResultList();
        if (results.isEmpty()) {
            throw new EntityNotFoundException("Reservation", number.getNumber());
        }
        return (Reservation) results.get(0);
    }
}
