package pl.com.bottega.cms.infrastructure;

import pl.com.bottega.cms.application.MovieCatalog;
import pl.com.bottega.cms.application.MovieDto;
import pl.com.bottega.cms.application.ShowingDto;
import pl.com.bottega.cms.model.InvalidActionException;
import pl.com.bottega.cms.model.Movie;
import pl.com.bottega.cms.model.Showing;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.text.Collator;
import java.time.LocalDate;
import java.util.*;

public class JPAMovieCatalog implements MovieCatalog {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<MovieDto> listAvailableMovies(Long cinemaId, LocalDate date) {

        String queryMovie = "SELECT DISTINCT m FROM Movie m " +
                "LEFT JOIN FETCH m.showings s " +
                "LEFT JOIN FETCH s.cinema c " +
                "WHERE c.id = :cinemaId " +
                "AND s.beginsAt BETWEEN :startHourOfDay AND :endHourOfDay " +
                "ORDER BY m.title ASC";

        Query query = entityManager.createQuery(queryMovie);
        query.setParameter("cinemaId", cinemaId);
        query.setParameter("startHourOfDay", date.atStartOfDay());
        query.setParameter("endHourOfDay", date.atStartOfDay().plusDays(1));

        List<Movie> movies = query.getResultList();

        List<MovieDto> movieDtos = new LinkedList<>();
        for (Movie movie : movies) {
            movieDtos.add(getMovieDtos(movie));
        }
        return movieDtos;
    }

    private MovieDto getMovieDtos(Movie movie) {
        MovieDto movieDto = new MovieDto();
        movieDto.setTitle(movie.getTitle());
        movieDto.setDescription(movie.getDescription());
        movieDto.setActors(movie.getActors());
        movieDto.setGenres(movie.getGenres());
        movieDto.setMinAge(movie.getMinAge());
        movieDto.setLength(movie.getLength());
        List<ShowingDto> showingDtos = createShowingDtos(movie);
        movieDto.setShowings(showingDtos);
        return movieDto;
    }

    private List<ShowingDto> createShowingDtos(Movie movie) {
        List<ShowingDto> showingDtos = new LinkedList<>();
        for (Showing showing : movie.getShowings()) {
            showingDtos.add(getShowingDtos(showing));
            showingDtos.sort(new Comparator<ShowingDto>() {
                @Override
                public int compare(ShowingDto o1, ShowingDto o2) {
                    return o1.getTime().compareTo(o2.getTime());
                }
            });
        }
        return showingDtos;
    }

    private ShowingDto getShowingDtos(Showing showing) {
        ShowingDto showingDto = new ShowingDto();
        showingDto.setId(showing.getId());
        showingDto.setTime(showing.getBeginsAt().toLocalTime());
        return showingDto;
    }

}
