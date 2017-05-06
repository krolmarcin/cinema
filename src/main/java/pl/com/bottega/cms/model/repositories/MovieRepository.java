package pl.com.bottega.cms.model.repositories;

import pl.com.bottega.cms.model.movie.Movie;

/**
 * Created by maciek on 09.04.2017.
 */
public interface MovieRepository {
    void put(Movie m);

    Movie get(Long id);

}
