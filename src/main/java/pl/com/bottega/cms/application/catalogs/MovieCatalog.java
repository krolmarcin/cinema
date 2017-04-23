package pl.com.bottega.cms.application.catalogs;

import pl.com.bottega.cms.application.dtos.MovieShowingsDto;

import java.time.LocalDate;
import java.util.List;

public interface MovieCatalog {

    List<MovieShowingsDto> getShowings(Long cinemaId, LocalDate date);

}
