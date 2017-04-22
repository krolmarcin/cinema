package pl.com.bottega.cms.ui;

import org.springframework.web.bind.annotation.*;
import pl.com.bottega.cms.application.*;
import pl.com.bottega.cms.infrastructure.GlobalParamsAndUtils;
import pl.com.bottega.cms.model.commands.CreateCinemaCommand;
import pl.com.bottega.cms.model.commands.CreateShowingsCommand;

import java.time.LocalDateTime;
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
    public List<MovieDto> showAvailableMoviesOnGivenDate(@PathVariable Long cinemaId, @RequestParam("date") String date) {
        GlobalParamsAndUtils globalParamsAndUtils = new GlobalParamsAndUtils();
        LocalDateTime startHour = globalParamsAndUtils.parseStringToLocalDateTime(date + "T00:00");
        LocalDateTime endHour = globalParamsAndUtils.parseStringToLocalDateTime(date + "T23:59");
        return movieCatalog.listAvailableMovies(cinemaId, startHour, endHour);
    }

}
