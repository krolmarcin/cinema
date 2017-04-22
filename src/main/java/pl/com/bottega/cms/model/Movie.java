package pl.com.bottega.cms.model;

import javax.persistence.*;

import pl.com.bottega.cms.model.commands.CreateMovieCommand;
import pl.com.bottega.cms.model.commands.DefineMoviePricesCommand;

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


    @OneToOne(cascade = CascadeType.ALL)
    private Pricing pricing;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "movieId")
    private Set<Showing> showings;

    Movie() {
    }

    public Movie(CreateMovieCommand cmd) {
        this.title = cmd.getTitle();
        this.description = cmd.getDescription();
        this.actors = cmd.getActors();
        this.genres = cmd.getGenres();
        this.minAge = cmd.getMinAge();
        this.length = cmd.getLength();
        this.pricing = new Pricing();
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

    public Set<Showing> getShowings() {
        return showings;
    }

    public void setShowings(Set<Showing> showings) {
        this.showings = showings;
    }

    public Pricing getPricing() {
        return pricing;
    }

    public void definePrices(DefineMoviePricesCommand dmpc){
        if (getPricing() == null){
            pricing = new Pricing(dmpc);
        }
        else
            pricing.updatePrices(dmpc);
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
