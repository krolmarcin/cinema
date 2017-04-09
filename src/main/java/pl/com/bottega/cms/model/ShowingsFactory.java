package pl.com.bottega.cms.model;

import org.springframework.beans.factory.annotation.Autowired;
import pl.com.bottega.cms.model.commands.CreateShowingsCommand;

import java.time.LocalDateTime;

/**
 * Created by maciek on 09.04.2017.
 */
public class ShowingsFactory {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    CinemaRepository cinemaRepository;

    public ShowingsFactory(){

    }

    public Showing createShowings(CreateShowingsCommand cmd){
        Showing showing = new Showing();
        Movie movie = movieRepository.get(cmd.getMovieId());
        showing.setMovie(movie);
        Cinema cinema = cinemaRepository.get(cmd.getCinemaId());
        showing.setCinema(cinema);
        LocalDateTime beginsAt = cmd.getBeginsAt();
        showing.setBeginsAt(beginsAt);
        return showing;
    }
}
