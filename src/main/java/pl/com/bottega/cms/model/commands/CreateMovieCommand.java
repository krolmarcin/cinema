package pl.com.bottega.cms.model.commands;

import java.util.Set;

import static pl.com.bottega.cms.model.commands.ValidationError.*;

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
        if (isEmpty(title))
            errors.add("title", REQUIRED.getValMsg());
        if (isEmpty(description))
            errors.add("description", REQUIRED.getValMsg());
        if (isEmpty(actors))
            errors.add("actors", REQUIRED.getValMsg() + NOT_EMPTY.getValMsg()+ NOT_NULL_VALUES.getValMsg());
        if (isEmpty(genres))
            errors.add("genres", REQUIRED.getValMsg() + NOT_EMPTY.getValMsg()+ NOT_NULL_VALUES.getValMsg());
        if (isEmpty(minAge) || isNotGreaterThanZero(minAge))
            errors.add("minAge", REQUIRED.getValMsg() + GREATER_THAN_ZERO.getValMsg());
        if (isEmpty(length) || isNotGreaterThanZero(length))
            errors.add("length", REQUIRED.getValMsg() + GREATER_THAN_ZERO.getValMsg());
    }
}