package pl.com.bottega.cms.model.reservation;

import java.time.LocalDateTime;

/**
 * Created by ogurekk on 2017-04-22.
 */
public interface ReservationNumberGenerator {

    public String generate(Long cinemaId, Long movieId, LocalDateTime date, int id);
}
