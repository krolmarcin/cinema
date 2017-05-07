package pl.com.bottega.cms.ui;

import org.springframework.web.bind.annotation.*;
import pl.com.bottega.cms.infrastructure.processes.AdminPanel;
import pl.com.bottega.cms.model.commands.CreateMovieCommand;
import pl.com.bottega.cms.model.commands.DefineMoviePricesCommand;

import java.math.BigDecimal;
import java.util.Map;

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

    @PutMapping("/{movieId}/prices")
    public void defineMoviePrices(@PathVariable Long movieId, @RequestBody Map<String, BigDecimal> prices){
        DefineMoviePricesCommand dmpc = new DefineMoviePricesCommand(prices);
        dmpc.setMovieId(movieId);
        adminPanel.defineMoviePrices(dmpc);
    }
}
