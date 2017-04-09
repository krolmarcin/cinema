package pl.com.bottega.cms.infrastructure;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import pl.com.bottega.cms.application.AdminPanel;
import pl.com.bottega.cms.model.CinemaRepository;
import pl.com.bottega.cms.model.MovieRepository;
import pl.com.bottega.cms.model.ShowingRepository;
import pl.com.bottega.cms.application.AdminPanel;
import pl.com.bottega.cms.application.implementation.StandardAdminPanel;

/**
 * Created by ogurekk on 2017-04-09.
 */
@org.springframework.context.annotation.Configuration
@EnableAsync
public class Configuration {

    @Bean
    public AdminPanel adminPanel() {
        return new StandardAdminPanel();
    }

    @Bean
    public CinemaRepository cinemaRepository() {
        return new JPACinemaRepository();
    }

    @Bean
    public MovieRepository movieRepository() {
        return new JPAMovieRepository();
    }

    @Bean
    public ShowingRepository showingRepository() {
        return new JPAShowingRepository();
    }
}
