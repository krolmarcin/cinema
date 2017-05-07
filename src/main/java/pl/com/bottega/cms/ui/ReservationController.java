package pl.com.bottega.cms.ui;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.com.bottega.cms.application.TicketPrinter;
import pl.com.bottega.cms.infrastructure.processes.PaymentCollector;
import pl.com.bottega.cms.model.commands.CollectPaymentCommand;
import pl.com.bottega.cms.model.reservation.CalculationResult;
import pl.com.bottega.cms.model.commands.CalculatePriceCommand;
import pl.com.bottega.cms.application.catalogs.ReservationCatalog;
import pl.com.bottega.cms.application.results.reservation.ReservationSearchResult;
import pl.com.bottega.cms.model.commands.CreateReservationCommand;
import pl.com.bottega.cms.infrastructure.processes.ReservationProcess;
import pl.com.bottega.cms.model.reservation.ReservationNumber;
import pl.com.bottega.cms.application.queries.ReservationQuery;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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

    @Autowired
    private TicketPrinter ticketPrinter;

    @Autowired
    private PaymentCollector paymentCollector;


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

    @PutMapping("/reservations/{reservationNumber}/payments")
    public void create(@RequestBody CollectPaymentCommand cmd) {
        paymentCollector.collectPayment(cmd);
    }

    @GetMapping("/reservations/{reservationNumber}/tickets")
    public void print(@PathVariable String reservationNumber, HttpServletResponse response) throws IOException {
        ReservationNumber number = new ReservationNumber(reservationNumber);
        byte[] tab = ticketPrinter.printTicket(number);
        response.getOutputStream().write(tab);
        response.setContentType("application/pdf");
    }
}
