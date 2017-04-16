package pl.com.bottega.cms.application.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.cms.application.AdminPanel;
import pl.com.bottega.cms.application.CinemaCatalog;
import pl.com.bottega.cms.model.*;
import pl.com.bottega.cms.model.commands.*;

import java.util.List;

import java.time.LocalDateTime;
import java.util.LinkedList;
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
        LocalDateTime beginsAt = cmd.getBeginsAt();
        List<Showing> showings = new LinkedList<>();
        // ify takie, bo tylko jedna z dwoch opcji moze byc -> jak oba sa null lub oba sa nie null to wyjatek
        if (calendar == null && beginsAt != null) {
            Showing showing = new Showing();
            showing.setBeginsAt(beginsAt);
            showings.add(showing);
        }
        else if (calendar != null && beginsAt == null) {
            showings.addAll(calendar.getShowings());
        }
        else {
            throw new IncompatibleInputException("CreateShowingsCommand");
        }
        for (Showing showing : showings) {
            showingRepository.put(showing);
        }
    }

}
