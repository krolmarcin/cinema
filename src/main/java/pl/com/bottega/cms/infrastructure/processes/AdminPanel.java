package pl.com.bottega.cms.infrastructure.processes;

import pl.com.bottega.cms.model.commands.CreateCinemaCommand;
import pl.com.bottega.cms.model.commands.CreateMovieCommand;
import pl.com.bottega.cms.model.commands.CreateShowingsCommand;
import pl.com.bottega.cms.model.commands.DefineMoviePricesCommand;


/**
 * Created by maciek on 09.04.2017.
 */
public interface AdminPanel {
    void createCinema(CreateCinemaCommand cmd);

    void createMovie(CreateMovieCommand cmd);

    void createShowings(CreateShowingsCommand cmd);

    void defineMoviePrices(DefineMoviePricesCommand prices);
}
