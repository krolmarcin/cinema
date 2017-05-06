package pl.com.bottega.cms.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.com.bottega.cms.model.repositories.ReservationProcess;
import pl.com.bottega.cms.application.dtos.CinemaHallDto;

/**
 * Created by ogurekk on 2017-04-22.
 */
@RestController
@RequestMapping("/shows")
public class ShowingsController {

    @Autowired
    private ReservationProcess reservationProcess;


    @GetMapping("/{showingId}/seats")
    public CinemaHallDto getSeats(@PathVariable Long showingId) {
        return reservationProcess.getSeats(showingId);
    }
}
