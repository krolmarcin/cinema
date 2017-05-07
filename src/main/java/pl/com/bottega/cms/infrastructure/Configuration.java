package pl.com.bottega.cms.infrastructure;

import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import pl.com.bottega.cms.application.TicketPrinter;
import pl.com.bottega.cms.application.catalogs.*;
import pl.com.bottega.cms.application.implementation.StandardPaymentCollector;
import pl.com.bottega.cms.application.implementation.StandardReservationProcess;
import pl.com.bottega.cms.infrastructure.processes.AdminPanel;
import pl.com.bottega.cms.infrastructure.processes.PaymentCollector;
import pl.com.bottega.cms.infrastructure.processes.ReservationProcess;
import pl.com.bottega.cms.infrastructure.repositories.*;
import pl.com.bottega.cms.application.implementation.StandardAdminPanel;
import pl.com.bottega.cms.infrastructure.tickets.ITextTicketPrinter;
import pl.com.bottega.cms.infrastructure.tickets.TicketMailer;
import pl.com.bottega.cms.model.repositories.*;
import pl.com.bottega.cms.model.reservation.PriceCalculator;
import pl.com.bottega.cms.model.reservation.ReservationNumberGenerator;
import pl.com.bottega.cms.model.reservation.StandardReservationNumberGenerator;
import pl.com.bottega.cms.model.showing.ShowingsFactory;

import java.util.concurrent.Executor;

/**
 * Created by ogurekk on 2017-04-09.
 */
@org.springframework.context.annotation.Configuration
@EnableAsync
public class Configuration extends AsyncConfigurerSupport {

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(2);
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("CMS-Async-Executor");
        executor.initialize();
        return executor;
    }

    @Bean
    public CinemaRepository cinemaRepository() {
        return new JPACinemaRepository();
    }

    @Bean
    public AdminPanel adminPanel() {
        return new StandardAdminPanel();
    }

    @Bean
    public CinemaCatalog cinemaCatalog() {
        return new JPACinemaCatalog();
    }

    @Bean
    public MovieRepository movieRepository() {
        return new JPAMovieRepository();
    }

    @Bean
    public ShowingRepository showingRepository() {
        return new JPAShowingRepository();
    }

    @Bean
    public ReservationNumberGenerator revervationNumberGenerator() {
        return new StandardReservationNumberGenerator();
    }

    @Bean
    public MovieCatalog movieCatalog() {
        return new JPAMovieCatalog();
    }

    @Bean
    public ReservationProcess reservationProcess() {
        return new StandardReservationProcess();
    }

    @Bean
    public ReservationCatalog reservationCatalog() {
        return new JPAReservationCatalog();
    }

    @Bean
    public PriceCalculator priceCalculator() {
        return new PriceCalculator(showingRepository());
    }

    @Bean
    public ShowingsFactory showingsFactory() {
        return new ShowingsFactory();
    }

    @Bean
    public ReservationRepository reservationRepository(){
        return new JPAReservationRepository();
    }

    @Bean
    public TicketPrinter ticketPrinter(ReservationRepository reservationRepository){
        return new ITextTicketPrinter(reservationRepository);
    }

    @Bean
    public TransactionRepository transactionRepository() {
        return new JPATransactionRepository();
    }

    @Bean
    public PaymentCollector paymentCollector() {
        return new StandardPaymentCollector();
    }

    @Bean
    public TicketMailer ticketMailer() {
        return new TicketMailer();
    }

}



