package pl.com.bottega.cms.integration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.cms.application.dtos.MovieShowingsDto;
import pl.com.bottega.cms.infrastructure.GlobalParamsAndUtils;
import pl.com.bottega.cms.application.catalogs.JPAMovieCatalog;
import pl.com.bottega.cms.model.movie.Movie;
import pl.com.bottega.cms.ui.InvalidActionException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class JPAMovieCatalogTest {

    @Autowired
    private JPAMovieCatalog movieCatalog;

    @Test
    @Sql("/fixtures/cinemaCatalog.sql")
    public void shouldListMoviesInCinemaWithShowings() {
        Long cinemaId = 1L;
        LocalDate date = LocalDate.of(2017, 04, 20);

        List<MovieShowingsDto> movies = movieCatalog.getShowings(cinemaId, date);

        assertThat(movies.size()).isEqualTo(2);
        assertThat(movies.get(0).getTitle()).isEqualTo("Pulp Fiction");
        assertThat(movies.get(1).getTitle()).isEqualTo("Pulp Fiction 2");
        assertThat(movies.get(0).getShowings().get(0).getTime().toString()).isEqualTo("07:15");
        assertThat(movies.get(0).getShowings().get(1).getTime().toString()).isEqualTo("10:15");
    }

    @Test(expected = InvalidActionException.class)
    @Sql("/fixtures/cinemaCatalog.sql")
    public void shouldReturnErrorMessageIfDateIsMissing(){
        Long cinemaId = 1L;
        LocalDate date = null;

        movieCatalog.getShowings(cinemaId, date);
    }

    @Test
    @Sql("/fixtures/cinemaCatalog.sql")
    public void shouldFindMoviesPrices() {
        Long cinemaId = 1L;
        LocalDate date = LocalDate.of(2017, 04, 20);

        List<MovieShowingsDto> movies = movieCatalog.getShowings(cinemaId, date);

        assertThat(movies.size()).isEqualTo(2);
        assertThat(movies.get(0).getPrices().get("regular")).isEqualTo(4.25);
        assertThat(movies.get(0).getPrices().get("student")).isEqualTo(3.25);
        assertThat(movies.get(0).getPrices().get("school")).isEqualTo(2.25);
        assertThat(movies.get(0).getPrices().get("children")).isEqualTo(0.00);
        }

}
