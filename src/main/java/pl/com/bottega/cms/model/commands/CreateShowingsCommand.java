package pl.com.bottega.cms.model.commands;


import java.time.LocalDateTime;

/**
 * Created by maciek on 09.04.2017.
 */
public class CreateShowingsCommand {

    private Long movieId;

    private Long cinemaId;

    private LocalDateTime beginsAt;

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public Long getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(Long cinemaId) {
        this.cinemaId = cinemaId;
    }

    public void setBeginsAt(LocalDateTime beginsAt) {
        this.beginsAt = beginsAt;
    }

    public LocalDateTime getBeginsAt() {
        return beginsAt;
    }
}
