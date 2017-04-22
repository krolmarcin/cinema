package pl.com.bottega.cms.application.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.cms.application.AdminPanel;
import pl.com.bottega.cms.model.commands.DefineMoviePricesCommand;
import pl.com.bottega.cms.model.*;
import pl.com.bottega.cms.model.commands.*;

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
        ShowingsArranger calendar = cmd.getCalendar();
        List<LocalDateTime> dates = cmd.getDates();
        if (calendar != null) {
            dates = calendar.getDates();
        }
        Long movieId = cmd.getMovieId();
        Long cinemaId = cmd.getCinemaId();
        for (LocalDateTime date : dates) {
            Showing showing = new Showing();
            showing.setBeginsAt(date);
            showing.setMovie(movieRepository.get(movieId));
            showing.setCinema(cinemaRepository.get(cinemaId));
            showing.setReservations(cmd.getReservations());
            showingRepository.put(showing);
        }
    }

    @Override
    public void defineMoviePrices(Long movieId, DefineMoviePricesCommand prices) {
        Movie movie = movieRepository.get(movieId);
        movie.definePrices(prices);
    }
}
