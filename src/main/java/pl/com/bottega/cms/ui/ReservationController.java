package pl.com.bottega.cms.ui;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.com.bottega.cms.model.CalculationResult;
import pl.com.bottega.cms.model.commands.CalculatePriceCommand;
import pl.com.bottega.cms.model.commands.CreateReservationCommand;
import pl.com.bottega.cms.infrastructure.ReservationProcess;
import pl.com.bottega.cms.model.reservation.ReservationNumber;

/**
 * Created by ogurekk on 2017-04-22.
 */
@RestController
public class ReservationController {

    @Autowired
    private ReservationProcess reservationProcess;


    @PutMapping("/reservations")
    public ReservationNumber create(@RequestBody CreateReservationCommand cmd) {
        return reservationProcess.create(cmd);
    }

    @PostMapping("/price_calculator")
    public CalculationResult calculatePrices(@RequestBody CalculatePriceCommand cmd){
        return reservationProcess.calculatePrices(cmd);
    }
}
