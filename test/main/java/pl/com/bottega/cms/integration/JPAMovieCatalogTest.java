package pl.com.bottega.cms.integration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.cms.application.MovieDto;
import pl.com.bottega.cms.infrastructure.GlobalParamsAndUtils;
import pl.com.bottega.cms.infrastructure.JPAMovieCatalog;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class JPAMovieCatalogTest {

    @Autowired
    private JPAMovieCatalog movieCatalog;

    private GlobalParamsAndUtils globalParamsAndUtils;

    @Test
    @Sql("/fixtures/cinemaCatalog.sql")
    public void shouldFindMoviesInCinema() {
        Long cinemaId = 1L;
        String date = "2017/04/20";
        LocalDateTime startDay = globalParamsAndUtils.parseStringToLocalDateTime(date + "T00:00");
        LocalDateTime endDay = globalParamsAndUtils.parseStringToLocalDateTime(date + "T23:59");

        List<MovieDto> movies = movieCatalog.listAvailableMovies(cinemaId, startDay, endDay);

        assertThat(movies.size()).isEqualTo(2);
        assertThat(movies.get(0).getTitle()).isEqualTo("Pulp Fiction");
        assertThat(movies.get(1).getTitle()).isEqualTo("Pulp Fiction 2");
    }

}
