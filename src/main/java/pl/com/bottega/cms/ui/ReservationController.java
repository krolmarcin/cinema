package pl.com.bottega.cms.ui;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.com.bottega.cms.application.catalogs.ReservationCatalog;
import pl.com.bottega.cms.application.results.ReservationSearchResult;
import pl.com.bottega.cms.model.commands.CreateReservationCommand;
import pl.com.bottega.cms.infrastructure.ReservationProcess;
import pl.com.bottega.cms.model.reservation.ReservationNumber;
import pl.com.bottega.cms.application.queries.ReservationQuery;

/**
 * Created by ogurekk on 2017-04-22.
 */
@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationProcess reservationProcess;

    @Autowired
    private ReservationCatalog reservationCatalog;


    @PutMapping
    public ReservationNumber create(@RequestBody CreateReservationCommand cmd) {
        return reservationProcess.create(cmd);
    }

    @GetMapping
    public ReservationSearchResult search(ReservationQuery reservationQuery) {
        return reservationCatalog.search(reservationQuery);
    }
}
