package pl.com.bottega.cms.infrastructure;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import pl.com.bottega.cms.application.catalogs.*;
import pl.com.bottega.cms.application.implementation.StandardReservationProcess;
import pl.com.bottega.cms.infrastructure.repositories.*;
import pl.com.bottega.cms.application.implementation.StandardAdminPanel;
import pl.com.bottega.cms.model.reservation.PriceCalculator;
import pl.com.bottega.cms.model.reservation.ReservationNumberGenerator;
import pl.com.bottega.cms.model.reservation.StandardReservationNumberGenerator;

/**
 * Created by ogurekk on 2017-04-09.
 */
@org.springframework.context.annotation.Configuration
@EnableAsync
public class Configuration {

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
    public PriceCalculator priceCalculator(){
        return new PriceCalculator();
    }

    }
