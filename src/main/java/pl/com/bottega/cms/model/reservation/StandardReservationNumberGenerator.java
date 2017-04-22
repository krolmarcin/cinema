package pl.com.bottega.cms.model.reservation;

import java.time.LocalDateTime;

import static pl.com.bottega.cms.infrastructure.GlobalParamsAndUtils.HASH_0;
import static pl.com.bottega.cms.infrastructure.GlobalParamsAndUtils.HASH_1;
import static pl.com.bottega.cms.infrastructure.GlobalParamsAndUtils.hash;

/**
 * Created by ogurekk on 2017-04-22.
 */
public class StandardReservationNumberGenerator implements ReservationNumberGenerator {

    @Override
    public String generate(Long cinemaId, Long movieId, LocalDateTime date, int id) {
        String dateString = date.toString();
        Long hourLong = Long.parseLong(dateString.substring(dateString.length()-5, dateString.length()-3)) * 100 +
                Long.parseLong(dateString.substring(dateString.length()-2, dateString.length()));
        return hash(cinemaId)+ "/" + hash(movieId) + "/" + hash(hourLong) + "/" + hash(Long.parseLong(id+""));
    }


}
