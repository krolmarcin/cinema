package pl.com.bottega.cms.model;

import javax.persistence.*;

import pl.com.bottega.cms.model.commands.CreateShowingsCommand;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

/**
 * Created by maciek on 09.04.2017.
 */
@Entity
public class Showing {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "movieId")
    private Movie movie;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cinemaId")
    private Cinema cinema;

    private LocalDateTime beginsAt;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "reservation_id")
    private Set<Reservation> reservations;

    public Showing() {
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

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }
}
