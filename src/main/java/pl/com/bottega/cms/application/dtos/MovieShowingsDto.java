package pl.com.bottega.cms.application.dtos;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MovieShowingsDto {

    private String title;
    private String description;
    private Set<String> actors;
    private Set<String> genres;
    private int minAge;
    private int length;
    private List<ShowingDto> showings;
    private Map<String, BigDecimal> prices;

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

    public List<ShowingDto> getShowings() {
        return showings;
    }

    public void setShowings(List<ShowingDto> showings) {
        this.showings = showings;
    }

    public Map<String, BigDecimal> getPrices() {
        return prices;
    }

    public void setPrices(Map<String, BigDecimal> prices) {
        this.prices = prices;
    }

}
