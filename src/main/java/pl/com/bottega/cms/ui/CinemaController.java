package pl.com.bottega.cms.ui;

import org.springframework.web.bind.annotation.*;
import pl.com.bottega.cms.application.AdminPanel;
import pl.com.bottega.cms.application.CinemaCatalog;
import pl.com.bottega.cms.application.CinemaDto;
import pl.com.bottega.cms.model.Cinema;
import pl.com.bottega.cms.model.commands.CreateCinemaCommand;

import java.util.List;

/**
 * Created by maciek on 09.04.2017.
 */
@RestController
@RequestMapping("/cinemas")
public class CinemaController {

    private CinemaCatalog cinemaCatalog;
    private AdminPanel adminPanel;

    public CinemaController(CinemaCatalog cinemaCatalog, AdminPanel adminPanel) {
        this.cinemaCatalog = cinemaCatalog;
        this.adminPanel = adminPanel;
    }

    @PutMapping
    public void create(@RequestBody CreateCinemaCommand cmd){
        adminPanel.createCinema(cmd);
    }

    @GetMapping
    public List<CinemaDto> showAll(){
        return cinemaCatalog.getCinemas();
    }
}
