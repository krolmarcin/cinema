package pl.com.bottega.cms.application.catalogs;

import org.springframework.beans.factory.annotation.Autowired;
import pl.com.bottega.cms.application.queries.ReservationQuery;
import pl.com.bottega.cms.application.results.ReservationSearchResult;
import pl.com.bottega.cms.model.reservation.PriceCalculator;
import pl.com.bottega.cms.model.reservation.Reservation;
import pl.com.bottega.cms.model.reservation.ReservationStatus;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by ogurekk on 2017-04-23.
 */
public class JPAReservationCatalog implements ReservationCatalog {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private PriceCalculator priceCalculator;

    @Override
    public List<ReservationSearchResult> search(ReservationQuery reservationQuery) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Reservation> criteriaQuery = criteriaBuilder.createQuery(Reservation.class);
        Root<Reservation> root = criteriaQuery.from(Reservation.class);
        Set<Predicate> predicates = new HashSet<>();
        String personalQuery = reservationQuery.getQuery();
        if (personalQuery != null) {
            predicates.add(criteriaBuilder.like(root.get("customer").get("lastName"), personalQuery));
        }
        ReservationStatus reservationStatus = reservationQuery.getStatus();
        if (reservationStatus != null) {
            predicates.add(criteriaBuilder.equal(root.get("reservationStatus"), reservationStatus));
        }
        criteriaQuery.where(predicates.toArray(new Predicate[] {}));
        Query query = entityManager.createQuery(criteriaQuery);
        List<Reservation> resultList = query.getResultList();
        List<ReservationSearchResult> reservationSearchResultList = reservationListToReservationSearchResultList(resultList);
        return reservationSearchResultList;
    }

    private ReservationSearchResult reservationToReservationSearchResult(Reservation reservation) {
        ReservationSearchResult reservationSearchResult = new ReservationSearchResult();
        reservationSearchResult.setNumber(reservation.getReservationNumber());
        reservationSearchResult.setSeats(reservation.getDetailedSeats());
        reservationSearchResult.setCustomer(reservation.getCustomer());
        reservationSearchResult.setStatus(reservation.getReservationStatus());
        return reservationSearchResult;
    }

    private List<ReservationSearchResult> reservationListToReservationSearchResultList(List<Reservation> reservationList) {
        List<ReservationSearchResult> reservationSearchResultList = new ArrayList<>();
        for (Reservation reservation : reservationList) {
            ReservationSearchResult reservationSearchResult = reservationToReservationSearchResult(reservation);
            reservationSearchResultList.add(reservationSearchResult);
        }
        return reservationSearchResultList;
    }
}
