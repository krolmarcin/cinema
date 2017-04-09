package pl.com.bottega.cms.application.implementation;

import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.cms.application.AdminPanel;
import pl.com.bottega.cms.model.Cinema;
import pl.com.bottega.cms.model.CinemaRepository;
import pl.com.bottega.cms.model.commands.CreateCinemaCommand;
import pl.com.bottega.cms.model.commands.CreateMovieCommand;
import pl.com.bottega.cms.model.commands.CreateShowingsCommand;

/**
 * Created by maciek on 09.04.2017.
 */
@Transactional
public class StandardAdminPanel implements AdminPanel {

    private CinemaRepository cinemaRepository;

    public StandardAdminPanel(CinemaRepository cinemaRepository) {
        this.cinemaRepository = cinemaRepository;
    }

    @Override
    public void createCinema(CreateCinemaCommand cmd) {
        Cinema cinema = new Cinema(cmd);
        cinemaRepository.put(cinema);
    }

    @Override
    public void createMovie(CreateMovieCommand cmd) {

    }

    @Override
    public void createShowings(CreateShowingsCommand cmd) {

    }
}
