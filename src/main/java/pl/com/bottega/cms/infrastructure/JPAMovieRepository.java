package pl.com.bottega.cms.infrastructure;

import pl.com.bottega.cms.model.Movie;
import pl.com.bottega.cms.model.MovieRepository;

/**
 * Created by maciek on 09.04.2017.
 */
public class JPAMovieRepository implements MovieRepository {
    @Override
    public void put(Movie m) {

    }

    @Override
    public Movie get(Long id) {
        return null;
    }
}
