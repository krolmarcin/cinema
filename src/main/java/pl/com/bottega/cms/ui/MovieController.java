package pl.com.bottega.cms.ui;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.com.bottega.cms.application.AdminPanel;
import pl.com.bottega.cms.model.commands.CreateMovieCommand;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private AdminPanel adminPanel;

    public MovieController(AdminPanel adminPanel) {
        this.adminPanel = adminPanel;
    }

    @PutMapping
    public void create(@RequestBody CreateMovieCommand cmd) {
        adminPanel.createMovie(cmd);
    }

}
