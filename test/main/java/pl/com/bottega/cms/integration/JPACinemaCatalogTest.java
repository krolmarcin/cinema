package pl.com.bottega.cms.integration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.cms.application.CinemaDto;
import pl.com.bottega.cms.infrastructure.JPACinemaCatalog;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class JPACinemaCatalogTest {

    @Autowired
    private JPACinemaCatalog catalog;

    @Test
    @Sql("/fixtures/cinemaCatalog.sql")
    public void shouldFindAllCinemas(){
        List<CinemaDto> cinemaDtoList = catalog.getCinemas();

        assertThat(cinemaDtoList.size()).isEqualTo(3);
    }

}
