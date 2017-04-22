package pl.com.bottega.cms.model.commands;

import pl.com.bottega.cms.infrastructure.validation.Validatable;

import java.util.Set;

public class CreateMovieCommand implements Validatable {

    private String title;
    private String description;
    private Set<String> actors;
    private Set<String> genres;
    private Integer minAge;
    private Integer length;

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
    public void validate(ValidationErrors errors) {
        ensureNotEmpty(title, "title", errors);
        ensureNotEmpty(description, "description", errors);
        ensureNotEmpty(actors, "actors", errors);
        ensureNotEmpty(genres, "genres", errors);
        ensureNotEmpty(minAge, "minAge", errors);
        ensureGreaterThanZero(minAge, "minAge", errors);
        ensureNotEmpty(length, "length", errors);
        ensureGreaterThanZero(length, "length", errors);
    }
}