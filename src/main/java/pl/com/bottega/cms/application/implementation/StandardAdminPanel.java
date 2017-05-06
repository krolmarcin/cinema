package pl.com.bottega.cms.application.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.cms.infrastructure.AdminPanel;
import pl.com.bottega.cms.infrastructure.repositories.CinemaRepository;
import pl.com.bottega.cms.infrastructure.repositories.MovieRepository;
import pl.com.bottega.cms.infrastructure.repositories.ShowingRepository;
import pl.com.bottega.cms.model.cinema.Cinema;
import pl.com.bottega.cms.model.commands.DefineMoviePricesCommand;
import pl.com.bottega.cms.model.commands.*;
import pl.com.bottega.cms.model.movie.Movie;
import pl.com.bottega.cms.model.reservation.Reservation;
import pl.com.bottega.cms.model.showing.Showing;
import pl.com.bottega.cms.model.showing.ShowingsArranger;
import pl.com.bottega.cms.model.showing.ShowingsFactory;
import pl.com.bottega.cms.ui.InvalidActionException;

import java.util.HashSet;
import java.util.List;

import java.time.LocalDateTime;

/**
 * Created by maciek on 09.04.2017.
 */
@Transactional
public class StandardAdminPanel implements AdminPanel {

    @Autowired
    private CinemaRepository cinemaRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ShowingRepository showingRepository;

    @Autowired
    private ShowingsFactory showingsFactory;

    @Override
    public void createCinema(CreateCinemaCommand cmd) {
        Cinema cinema = new Cinema(cmd);
        if (cinemaRepository.exists(cinema.getName(), cinema.getCity())) {
            throw new InvalidActionException(String.format("Cinema '%s' in '%s' has already been created", cinema.getName(), cinema.getCity()));
        }
        cinemaRepository.put(cinema);
    }

    @Override
    public void createMovie(CreateMovieCommand cmd) {
        Movie movie = new Movie(cmd);
        movieRepository.put(movie);
    }

    @Override
    public void createShowings(CreateShowingsCommand cmd) {
        Movie movie = movieRepository.get(cmd.getMovieId());
        Cinema cinema = cinemaRepository.get(cmd.getCinemaId());
        List<Showing> showings = showingsFactory.createShowings(movie, cinema, cmd);
        for (Showing showing : showings) {
            showingRepository.put(showing);
        }
    }

    @Override
    public void defineMoviePrices(DefineMoviePricesCommand prices) {
        Movie movie = movieRepository.get(prices.getMovieId());
        if (movie == null)
            throw new CommandInvalidException("movieId", "Movie doesn't exist");
        movie.definePrices(prices);
    }
}
