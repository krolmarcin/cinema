package pl.com.bottega.cms.model.commands;

import pl.com.bottega.cms.model.Cinema;
import pl.com.bottega.cms.model.Movie;

import java.time.LocalDateTime;

/**
 * Created by maciek on 09.04.2017.
 */
public class CreateShowingsCommand {

    private Movie movie;

    private Cinema cinema;

    private LocalDateTime beginsAt;

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    public void setBeginsAt(LocalDateTime beginsAt) {
        this.beginsAt = beginsAt;
    }

    public LocalDateTime getBeginsAt() {
        return beginsAt;
    }
}
