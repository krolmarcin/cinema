package pl.com.bottega.cms.infrastructure;

import pl.com.bottega.cms.application.CinemaCatalog;
import pl.com.bottega.cms.application.CinemaDto;
import pl.com.bottega.cms.model.Cinema;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by maciek on 09.04.2017.
 */
public class JPACinemaCatalog implements CinemaCatalog {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<CinemaDto> getCinemas() {
        Query query = entityManager.createQuery("FROM Cinema c");
        List<Cinema> cinemas = query.getResultList();
        List<CinemaDto> cinemaDtos = new LinkedList<>();
        for (Cinema cinema : cinemas) {
            cinemaDtos.add(cinemaToCinemaDto(cinema));
        }
        return cinemaDtos;

    }

    private CinemaDto cinemaToCinemaDto(Cinema cinema) {
        CinemaDto cinemaDto = new CinemaDto();
        cinemaDto.setId(cinema.getId());
        cinemaDto.setName(cinema.getName());
        cinemaDto.setCity(cinema.getCity());
        return cinemaDto;
    }
}
