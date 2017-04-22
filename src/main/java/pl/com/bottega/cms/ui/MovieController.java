package pl.com.bottega.cms.ui;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;
import pl.com.bottega.cms.application.AdminPanel;
import pl.com.bottega.cms.model.commands.CreateMovieCommand;
import pl.com.bottega.cms.model.commands.DefineTicketPriceCommand;

import java.io.IOException;
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
    public void defineTicketPrice(@PathVariable Long movieId, @RequestBody String inputJson) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, BigDecimal> ticketPrices = mapper.readValue(inputJson, new TypeReference<Map<String,BigDecimal>>(){});
        DefineTicketPriceCommand cmd = new DefineTicketPriceCommand();
        cmd.setTicketPrices(ticketPrices);
        cmd.setMovieId(movieId);
        adminPanel.defineTicketPrices(cmd);
    }
}
