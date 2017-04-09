package pl.com.bottega.cms.model;

import org.springframework.data.annotation.Id;
import pl.com.bottega.cms.model.commands.CreateShowingsCommand;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import java.time.LocalDateTime;

/**
 * Created by maciek on 09.04.2017.
 */
@Entity
public class Showing {

    @Id
    @GeneratedValue
    private Long id;

    private Movie movie;

    private Cinema cinema;

    private LocalDateTime beginsAt;

    Showing(){}

    public Showing(CreateShowingsCommand cmd){
        this.movie = cmd.getMovie();
        this.cinema = cmd.getCinema();
        this.beginsAt = cmd.getBeginsAt();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getBeginsAt() {
        return beginsAt;
    }

    public void setBeginsAt(LocalDateTime beginsAt) {
        this.beginsAt = beginsAt;
    }

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
}
