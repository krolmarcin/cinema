package pl.com.bottega.cms.application;

import pl.com.bottega.cms.model.Movie;

import java.util.Set;

/**
 * Created by maciek on 09.04.2017.
 */
public class CinemaDto {

    private Long id;

    private String name;
    private String city;

    private Set<Movie> movies;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public Set<Movie> getMovies() {
        return movies;
    }
}
