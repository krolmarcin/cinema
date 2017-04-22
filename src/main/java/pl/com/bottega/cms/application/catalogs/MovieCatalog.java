package pl.com.bottega.cms.application.catalogs;

import pl.com.bottega.cms.application.dtos.MovieDto;

import java.time.LocalDate;
import java.util.List;

public interface MovieCatalog {

    List<MovieDto> listAvailableMovies(Long cinemaId, LocalDate date);

}
