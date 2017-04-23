package pl.com.bottega.cms.application.catalogs;

import org.springframework.beans.factory.annotation.Autowired;
import pl.com.bottega.cms.application.queries.ReservationQuery;
import pl.com.bottega.cms.application.results.reservation.MovieWrapper;
import pl.com.bottega.cms.application.results.reservation.ReservationSearchResult;
import pl.com.bottega.cms.application.results.reservation.ShowingWrapper;
import pl.com.bottega.cms.infrastructure.ReservationProcess;
import pl.com.bottega.cms.infrastructure.repositories.ShowingRepository;
import pl.com.bottega.cms.model.reservation.Reservation;
import pl.com.bottega.cms.model.reservation.ReservationStatus;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.time.LocalDateTime;
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
    private ReservationProcess reservationProcess;

    @Autowired
    private ShowingRepository showingRepository;

    @Override
    public List<ReservationSearchResult> search(ReservationQuery reservationQuery) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Reservation> criteriaQuery = criteriaBuilder.createQuery(Reservation.class);
        Root<Reservation> root = criteriaQuery.from(Reservation.class);
        Set<Predicate> predicates = new HashSet<>();
        String personalQuery = reservationQuery.getQuery();
        addPredicates(predicates, criteriaBuilder, root, new String[]{"customer", "lastName"}, personalQuery);
        ReservationStatus reservationStatus = reservationQuery.getStatus();
        addPredicates(predicates, criteriaBuilder, root, new String[]{"reservationStatus"}, reservationStatus);
        criteriaQuery.where(predicates.toArray(new Predicate[] {}));
        Query query = entityManager.createQuery(criteriaQuery);
        List<Reservation> resultList = query.getResultList();
        List<ReservationSearchResult> reservationSearchResultList = reservationListToReservationSearchResultList(resultList);
        return reservationSearchResultList;
    }

    private void addPredicates(Set<Predicate> predicates, CriteriaBuilder criteriaBuilder, Root root, String[] fields, Object value) {
        if (value != null) {
            Path path = root;
            for (String field : fields) {
                path = path.get(field);
            }
            predicates.add(criteriaBuilder.equal(path, value));
        }
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
        //CalculatePriceCommand cmd = new CalculatePriceCommand();
        //cmd.setShowId(reservation.getShowing().getId());
        //cmd.setTickets()
        //CalculationResult calculationResult = reservationProcess.calculatePrices(cmd);
        //BigDecimal totalPrice = calculationResult.getTotalPrice();
        //reservationSearchResult.setTotalPrice(totalPrice);
        reservationSearchResult.setStatus(reservation.getReservationStatus());
        return reservationSearchResult;
    }

    private List<ReservationSearchResult> reservationListToReservationSearchResultList(List<Reservation> reservationList) {
        List<ReservationSearchResult> reservationSearchResultList = new ArrayList<>();
        for (Reservation reservation : reservationList) {
            System.out.println(reservation.getShowing().getBeginsAt());
            if (reservation.getShowing().getBeginsAt().plusMinutes(reservation.getShowing().getMovie().getLength()).compareTo(LocalDateTime.now()) >= 0) {
                System.out.println(reservation.getShowing().getBeginsAt());
                ReservationSearchResult reservationSearchResult = reservationToReservationSearchResult(reservation);
                reservationSearchResultList.add(reservationSearchResult);
            }
        }
        return reservationSearchResultList;
    }
}
