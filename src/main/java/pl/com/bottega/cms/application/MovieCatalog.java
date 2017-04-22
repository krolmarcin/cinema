package pl.com.bottega.cms.application;

import java.time.LocalDate;
import java.util.List;

public interface MovieCatalog {

    List<MovieDto> listAvailableMovies(Long cinemaId, LocalDate date);

}
