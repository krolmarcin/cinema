package pl.com.bottega.cms.model;

import java.time.LocalDateTime;

/**
 * Created by ogurekk on 2017-04-22.
 */
public class StandardReservationNumberGenerator implements ReservationNumberGenerator {

    @Override
    public String generate(Long cinemaId, Long movieId, LocalDateTime date, int id) {
        return cinemaId + "//" + movieId + "//" + date + "//" + id;
    }
}
