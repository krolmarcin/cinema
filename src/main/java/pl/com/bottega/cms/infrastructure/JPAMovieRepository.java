package pl.com.bottega.cms.infrastructure;

import pl.com.bottega.cms.model.EntityNotFoundException;
import pl.com.bottega.cms.model.Movie;
import pl.com.bottega.cms.model.MovieRepository;
import pl.com.bottega.cms.model.TicketPrice;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by maciek on 09.04.2017.
 */
public class JPAMovieRepository implements MovieRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void put(Movie m) {
        entityManager.persist(m);
    }

    @Override
    public Movie get(Long id) {
        Movie movie = entityManager.find(Movie.class, id);
        if (movie == null) {
            throw new EntityNotFoundException(String.format("Movie id %s does not exist", id));
        }
        return movie;


    }

    @Override
    public void putTicketPrice(TicketPrice ticketPrice) {
        entityManager.persist(ticketPrice);
    }

    @Override
    public void updateTicketPrice(TicketPrice ticketPrice) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaUpdate<TicketPrice> update = criteriaBuilder.createCriteriaUpdate(TicketPrice.class);
        Root<TicketPrice> root = update.from(TicketPrice.class);
        update.set("regular", ticketPrice.getRegular());
        update.set("student", ticketPrice.getStudent());
        update.set("school", ticketPrice.getSchool());
        update.set("children", ticketPrice.getChildren());
        update.where(criteriaBuilder.equal(root.get("movie"), ticketPrice.getMovie()));
        entityManager.createQuery(update).executeUpdate();
    }

    @Override
    public TicketPrice getTicketPriceFor(Movie movie) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<TicketPrice> criteriaQuery = criteriaBuilder.createQuery(TicketPrice.class);
        Root<TicketPrice> root = criteriaQuery.from(TicketPrice.class);
        criteriaQuery.where(criteriaBuilder.equal(root.get("movie"), movie));
        TypedQuery<TicketPrice> query = entityManager.createQuery(criteriaQuery);
        List<TicketPrice> results = query.getResultList();
        if (!query.getResultList().isEmpty()) {
            TicketPrice ticketPrice = results.get(0);
            return ticketPrice;
        } else
            throw new EntityNotFoundException(String.format("Ticket price for movie id %s does not exist", movie.getId()));
    }

    @Override
    public boolean checkIfExistPriceFor(Movie movie) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<TicketPrice> criteriaQuery = criteriaBuilder.createQuery(TicketPrice.class);
        Root<TicketPrice> root = criteriaQuery.from(TicketPrice.class);
        criteriaQuery.where(criteriaBuilder.equal(root.get("movie"), movie));
        TypedQuery<TicketPrice> query = entityManager.createQuery(criteriaQuery);
        List<TicketPrice> results = query.getResultList();
        if (!query.getResultList().isEmpty())
            return true;
        return false;

    }

}
