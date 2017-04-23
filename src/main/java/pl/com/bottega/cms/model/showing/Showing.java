package pl.com.bottega.cms.model.showing;

import javax.persistence.*;

import pl.com.bottega.cms.model.cinema.Cinema;
import pl.com.bottega.cms.model.reservation.CinemaHall;
import pl.com.bottega.cms.model.reservation.Reservation;
import pl.com.bottega.cms.model.movie.Movie;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import java.time.LocalDateTime;
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
    @JoinColumn(name = "showing_id")
    private Set<Reservation> reservations;

    @Transient
    private CinemaHall cinemaHall;

    public Showing() {
        this.cinemaHall = CinemaHall.STANDARD;
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
