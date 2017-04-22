package pl.com.bottega.cms.ui;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.com.bottega.cms.application.CreateReservationCommand;
import pl.com.bottega.cms.application.ReservationProcess;
import pl.com.bottega.cms.model.ReservationNumber;

/**
 * Created by ogurekk on 2017-04-22.
 */
@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationProcess reservationProcess;


    @PutMapping
    public ReservationNumber create(@RequestBody CreateReservationCommand cmd) {
        return reservationProcess.create(cmd);
    }
}
