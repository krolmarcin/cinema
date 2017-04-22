package pl.com.bottega.cms.ui;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import pl.com.bottega.cms.application.catalogs.CinemaCatalog;
import pl.com.bottega.cms.application.catalogs.MovieCatalog;
import pl.com.bottega.cms.application.dtos.CinemaDto;
import pl.com.bottega.cms.application.dtos.MovieDto;
import pl.com.bottega.cms.infrastructure.AdminPanel;
import pl.com.bottega.cms.model.commands.CreateCinemaCommand;
import pl.com.bottega.cms.model.commands.CreateShowingsCommand;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by maciek on 09.04.2017.
 */
@RestController
@RequestMapping("/cinemas")
public class CinemaController {

    private CinemaCatalog cinemaCatalog;
    private MovieCatalog movieCatalog;
    private AdminPanel adminPanel;

    public CinemaController(CinemaCatalog cinemaCatalog, AdminPanel adminPanel, MovieCatalog movieCatalog) {
        this.cinemaCatalog = cinemaCatalog;
        this.adminPanel = adminPanel;
        this.movieCatalog = movieCatalog;
    }

    @PutMapping
    public void create(@RequestBody CreateCinemaCommand cmd) {
        adminPanel.createCinema(cmd);
    }

    @GetMapping
    public List<CinemaDto> showAll() {
        return cinemaCatalog.getCinemas();
    }

    @PutMapping("/{cinemaId}/shows")
    public void createShowings(@PathVariable Long cinemaId, @RequestBody CreateShowingsCommand cmd) {
        cmd.setCinemaId(cinemaId);
        adminPanel.createShowings(cmd);
    }

    @GetMapping("/{cinemaId}/movies")
    public List<MovieDto> getShowings(@PathVariable Long cinemaId, @RequestParam("date") @DateTimeFormat(pattern = "yyyy/MM/dd") LocalDate date) {
        return movieCatalog.listAvailableMovies(cinemaId, date);
    }

}
