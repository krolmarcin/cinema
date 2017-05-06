package pl.com.bottega.cms.infrastructure.repositories;

import pl.com.bottega.cms.model.repositories.ReservationRepository;
import pl.com.bottega.cms.model.reservation.Reservation;
import pl.com.bottega.cms.model.reservation.ReservationNumber;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by maciek on 06.05.2017.
 */
public class JPAReservationRepository implements ReservationRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Reservation get(ReservationNumber number) {
        Reservation reservation = entityManager.find(Reservation.class, number.getNumber());
        if (reservation == null){
            throw new EntityNotFoundException("Reservation", number.getNumber());
        }
        return reservation;
    }
}
