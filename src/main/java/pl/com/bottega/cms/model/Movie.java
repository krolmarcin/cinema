package pl.com.bottega.cms.model;

import javax.persistence.*;
import java.util.HashSet;

import org.hibernate.annotations.Cascade;
import pl.com.bottega.cms.model.commands.CreateMovieCommand;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Set;

@Entity
public class Movie {

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String description;

    @ElementCollection
    private Set<String> actors;

    @ElementCollection
    private Set<String> genres;

    private int minAge;
    private int length;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "cinemaId")
    private Set<Cinema> cinemas;

    Movie() {
    }

    public Movie(CreateMovieCommand cmd) {
        this.title = cmd.getTitle();
        this.description = cmd.getDescription();
        this.actors = cmd.getActors();
        this.genres = cmd.getGenres();
        this.minAge = cmd.getMinAge();
        this.length = cmd.getLength();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<String> getActors() {
        return actors;
    }

    public void setActors(Set<String> actors) {
        this.actors = actors;
    }

    public Set<String> getGenres() {
        return genres;
    }

    public void setGenres(Set<String> genres) {
        this.genres = genres;
    }

    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (this == null || obj == null) {
            return false;
        }
        if (!(obj instanceof Movie)) {
            return false;
        }
        Movie other = (Movie) obj;
        return (this.id == other.id);
    }
}
