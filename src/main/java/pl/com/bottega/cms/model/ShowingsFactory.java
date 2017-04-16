package pl.com.bottega.cms.model;

import org.springframework.beans.factory.annotation.Autowired;
import pl.com.bottega.cms.model.commands.CreateShowingsCommand;

import java.time.LocalDateTime;

/**
 * Created by maciek on 09.04.2017.
 */
public class ShowingsFactory {

    public ShowingsFactory() {

    }

    public Showing createShowings(Movie movie, Cinema cinema, CreateShowingsCommand cmd) {
        Showing showing = new Showing();
        showing.setMovie(movie);
        showing.setCinema(cinema);
        LocalDateTime beginsAt = cmd.getBeginsAt();
        showing.setBeginsAt(beginsAt);
        return showing;
    }
}
