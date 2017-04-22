package pl.com.bottega.cms.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.com.bottega.cms.application.AdminPanel;
import pl.com.bottega.cms.model.CinemaHall;
import pl.com.bottega.cms.model.CinemaHallDto;
import pl.com.bottega.cms.model.ShowingRepository;

/**
 * Created by ogurekk on 2017-04-22.
 */
@RestController
@RequestMapping("/shows")
public class ShowingsController {

    @Autowired
    private ShowingRepository showingRepository;


    @GetMapping("/{showingId}/seats")
    public CinemaHallDto getSeats(@PathVariable Long showingId) {
        CinemaHall cinemaHall = CinemaHall.STANDARD;
        cinemaHall.updateReservations(showingRepository.getReservations(showingId));
        CinemaHallDto cinemaHallDto = new CinemaHallDto(cinemaHall.getSeatConfiguration());
        return cinemaHallDto;
    }
}
