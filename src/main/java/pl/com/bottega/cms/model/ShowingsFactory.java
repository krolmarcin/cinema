package pl.com.bottega.cms.model;

import org.springframework.beans.factory.annotation.Autowired;
import pl.com.bottega.cms.model.commands.CreateShowingsCommand;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by maciek on 09.04.2017.
 */
public class ShowingsFactory {

    public ShowingsFactory() {

    }

    public List<Showing> createShowings(Movie movie, Cinema cinema, CreateShowingsCommand cmd) {
        List<LocalDateTime> dates = cmd.getDates();
        List<Showing> showings = new LinkedList<>();
        for (LocalDateTime date : dates) {
            Showing showing = new Showing();
            showing.setMovie(movie);
            showing.setCinema(cinema);
            showing.setBeginsAt(date);
            showings.add(showing);
        }
        return showings;
    }
}
