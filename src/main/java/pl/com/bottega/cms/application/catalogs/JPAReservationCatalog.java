package pl.com.bottega.cms.application.catalogs;

import org.springframework.beans.factory.annotation.Autowired;
import pl.com.bottega.cms.application.queries.ReservationQuery;
import pl.com.bottega.cms.application.dtos.MovieWrapper;
import pl.com.bottega.cms.application.results.reservation.ReservationSearchResult;
import pl.com.bottega.cms.application.dtos.ShowingWrapper;
import pl.com.bottega.cms.infrastructure.ReservationProcess;
import pl.com.bottega.cms.infrastructure.repositories.ShowingRepository;
import pl.com.bottega.cms.model.reservation.CalculationItem;
import pl.com.bottega.cms.model.reservation.Reservation;
import pl.com.bottega.cms.model.reservation.ReservationItem;
import pl.com.bottega.cms.model.reservation.ReservationStatus;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static pl.com.bottega.cms.infrastructure.GlobalParamsAndUtils.now;

/**
 * Created by ogurekk on 2017-04-23.
 */
public class JPAReservationCatalog implements ReservationCatalog {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ReservationProcess reservationProcess;

    @Autowired
    private ShowingRepository showingRepository;

    @Override
    public List<ReservationSearchResult> search(ReservationQuery reservationQuery) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Reservation> criteriaQuery = criteriaBuilder.createQuery(Reservation.class);
        Root<Reservation> root = criteriaQuery.from(Reservation.class);
        Set<Predicate> predicates = new HashSet<>();
        String personalDataQuery = reservationQuery.getQuery();
        if (personalDataQuery != null) {
            predicates.add(criteriaBuilder.like(root.get("customer").get("lastName"), personalDataQuery));
        }
        ReservationStatus reservationStatus = reservationQuery.getStatus();
        if (reservationStatus != null) {
            predicates.add(criteriaBuilder.equal(root.get("reservationStatus"), reservationStatus));
        }
        predicates.add(criteriaBuilder.greaterThan(root.get("showing").get("beginsAt"), now()));
        criteriaQuery.where(predicates.toArray(new Predicate[] {}));
        Query query = entityManager.createQuery(criteriaQuery);
        List<Reservation> resultList = query.getResultList();
        List<ReservationSearchResult> reservationSearchResultList = reservationListToReservationSearchResultList(resultList);
        return reservationSearchResultList;
    }



    private ReservationSearchResult reservationToReservationSearchResult(Reservation reservation) {
        ReservationSearchResult reservationSearchResult = new ReservationSearchResult();
        ShowingWrapper showingWrapper = new ShowingWrapper();
        showingWrapper.setId(reservation.getShowing().getId());
        showingWrapper.setTime(reservation.getShowing().getBeginsAt());
        reservationSearchResult.setShow(showingWrapper);
        MovieWrapper movieWrapper = new MovieWrapper();
        movieWrapper.setId(reservation.getShowing().getMovie().getId());
        movieWrapper.setTitle(reservation.getShowing().getMovie().getTitle());
        reservationSearchResult.setMovie(movieWrapper);
        reservationSearchResult.setNumber(reservation.getReservationNumber());
        reservationSearchResult.setSeats(reservation.getDetailedSeats());
        reservationSearchResult.setCustomer(reservation.getCustomer());
        Set<ReservationItem> reservationItems = reservation.getReservationItems();
        List<CalculationItem> calculationItems = new ArrayList<>();
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (ReservationItem reservationItem : reservationItems) {
            CalculationItem calculationItem = new CalculationItem(reservationItem, reservation.getShowing().getMovie().getPricing().getPriceMap().get(reservationItem.getKind()));
            calculationItems.add(calculationItem);
            totalPrice = totalPrice.add(calculationItem.getTotalPrice());
        }
        reservationSearchResult.setTickets(calculationItems);
        reservationSearchResult.setTotalPrice(totalPrice);
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
