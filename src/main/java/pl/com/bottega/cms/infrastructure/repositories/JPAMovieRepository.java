package pl.com.bottega.cms.infrastructure.repositories;

import pl.com.bottega.cms.model.repositories.MovieRepository;
import pl.com.bottega.cms.model.movie.Movie;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
            throw new EntityNotFoundException("Movie", id);
        }
        return movie;


    }

}
