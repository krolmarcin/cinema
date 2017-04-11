package pl.com.bottega.cms.infrastructure;

import pl.com.bottega.cms.model.Movie;
import pl.com.bottega.cms.model.MovieNotFoundException;
import pl.com.bottega.cms.model.MovieRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by maciek on 09.04.2017.
 */
public class JPAMovieRepository implements MovieRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void put(Movie m) {entityManager.persist(m);
    }

    @Override
    public Movie get(Long id) {
        Movie movie = entityManager.find(Movie.class, id);
        if (movie == null) {
            throw new MovieNotFoundException(String.format("Movie id %s not exists", id));
        }
        return movie;
    }
}
