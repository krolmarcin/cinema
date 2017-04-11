package pl.com.bottega.cms.model.commands;

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
    public void validate(Validatable.ValidationErrors errors) {
        if (isEmpty(title))
            errors.add("title", "can't be blank");
        if (isEmpty(description))
            errors.add("description", "can't be blank");
        if (isEmpty(actors))
            errors.add("actors", "can't be blank or actors field can't contain blank fields or can't be empty");
        if (isEmpty(genres))
            errors.add("genres", "can't be blank or genres field can't contain blank fields or can't be empty");
        if (minAge == null || !(minAge instanceof Integer) || minAge <= 0)
            errors.add("minAge", "can't be blank, and must be number > 0");
        if (length == null || !(length instanceof Integer) || length <= 0)
            errors.add("length", "can't be blank, and must be number > 0");
    }
}