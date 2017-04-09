package pl.com.bottega.cms.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by maciek on 09.04.2017.
 */
public class Cinema {

    private Long id;
    private String name;
    private String city;

    private Set<Movie> movies;


    Cinema(){}

    public Cinema(CreateCinemaCommand cmd){
        this.name = cmd.getName();
        this.city = cmd.getCity();
        this.movies = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Set<Movie> getMovies() {
        return movies;
    }

    public void setMovies(Set<Movie> movies) {
        this.movies = movies;
    }
}
