package pl.com.bottega.cms.model.commands;

import java.util.Set;

public class CreateMovieCommand implements Validatable{

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
        if (actors == null)
            errors.add("actors", "can't be blank");
        if (genres == null)
            errors.add("genres", "can't be blank");
        if (minAge == null || !(minAge instanceof Integer))
            errors.add("minAge", "can't be blank, and must be number");
        if (length == null || !(minAge instanceof Integer))
            errors.add("length", "can't be blank, and must be number");


    }
}