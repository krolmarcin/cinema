package pl.com.bottega.cms.application;

import java.time.LocalDateTime;
import java.util.List;

public interface MovieCatalog {

    List<MovieDto> listAvailableMovies(Long cinemaId, LocalDateTime startHour, LocalDateTime endHour);

}
