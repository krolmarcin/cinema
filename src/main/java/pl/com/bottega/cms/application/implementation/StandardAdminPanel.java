package pl.com.bottega.cms.application.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.cms.application.AdminPanel;
import pl.com.bottega.cms.application.CinemaCatalog;
import pl.com.bottega.cms.model.*;
import pl.com.bottega.cms.model.commands.CreateCinemaCommand;
import pl.com.bottega.cms.model.commands.CreateMovieCommand;
import pl.com.bottega.cms.model.commands.CreateShowingsCommand;

import java.util.List;

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
        cinemaRepository.put(cinema);
    }

    @Override
    public void createMovie(CreateMovieCommand cmd) {
        Movie movie = new Movie(cmd);
        movieRepository.put(movie);

    }

    @Override
    public void createShowings(CreateShowingsCommand cmd) {

    }
}
