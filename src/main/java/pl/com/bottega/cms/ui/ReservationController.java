package pl.com.bottega.cms.ui;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.com.bottega.cms.model.reservation.CalculationResult;
import pl.com.bottega.cms.model.commands.CalculatePriceCommand;
import pl.com.bottega.cms.application.catalogs.ReservationCatalog;
import pl.com.bottega.cms.application.results.ReservationSearchResult;
import pl.com.bottega.cms.model.commands.CreateReservationCommand;
import pl.com.bottega.cms.infrastructure.ReservationProcess;
import pl.com.bottega.cms.model.reservation.ReservationNumber;
import pl.com.bottega.cms.application.queries.ReservationQuery;

import java.util.List;

/**
 * Created by ogurekk on 2017-04-22.
 */
@RestController
public class ReservationController {

    @Autowired
    private ReservationProcess reservationProcess;

    @Autowired
    private ReservationCatalog reservationCatalog;


    @PutMapping("/reservations")
    public ReservationNumber create(@RequestBody CreateReservationCommand cmd) {
        return reservationProcess.create(cmd);
    }

    @GetMapping("/reservations")
    public List<ReservationSearchResult> search(ReservationQuery reservationQuery) {
        return reservationCatalog.search(reservationQuery);
    }

    @PostMapping("/price_calculator")
    public CalculationResult calculatePrices(@RequestBody CalculatePriceCommand cmd){
        return reservationProcess.calculatePrices(cmd);
    }
}
