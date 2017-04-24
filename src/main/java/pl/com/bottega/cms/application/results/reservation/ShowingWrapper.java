package pl.com.bottega.cms.application.results.reservation;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

/**
 * Created by ogurekk on 2017-04-23.
 */
public class ShowingWrapper {
    private Long id;

    @JsonFormat(pattern = "yyyy/MM/dd hh:mm")
    private LocalDateTime time;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
