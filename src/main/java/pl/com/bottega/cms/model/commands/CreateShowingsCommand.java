package pl.com.bottega.cms.model.commands;


import java.time.LocalDateTime;

/**
 * Created by maciek on 09.04.2017.
 */
public class CreateShowingsCommand {

    private LocalDateTime beginsAt;

    private Long cinemaId;


    public void setBeginsAt(LocalDateTime beginsAt) {
        this.beginsAt = beginsAt;
    }

    public LocalDateTime getBeginsAt() {
        return beginsAt;
    }

    public void setCinemaId(Long cinemaId) {
        this.cinemaId = cinemaId;
    }

    public Long getCinemaId() {
        return cinemaId;
    }

}
