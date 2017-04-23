package pl.com.bottega.cms.model.reservation;

import pl.com.bottega.cms.model.showing.Showing;

import java.time.LocalDateTime;

import static pl.com.bottega.cms.infrastructure.GlobalParamsAndUtils.HASH_0;
import static pl.com.bottega.cms.infrastructure.GlobalParamsAndUtils.HASH_1;
import static pl.com.bottega.cms.infrastructure.GlobalParamsAndUtils.hash;

/**
 * Created by ogurekk on 2017-04-22.
 */
public class StandardReservationNumberGenerator implements ReservationNumberGenerator {

    @Override
    public String generate(Showing showing) {
        Long cinemaId = showing.getCinema().getId();
        Long movieId = showing.getMovie().getId();
        LocalDateTime beginsAt = showing.getBeginsAt();
        String beginsAtString = beginsAt.toString();
        Long beginsAtLong = Long.parseLong(beginsAtString.substring(beginsAtString.length()-5, beginsAtString.length()-3)) * 100 +
                Long.parseLong(beginsAtString.substring(beginsAtString.length()-2, beginsAtString.length()));
        Long number = Long.parseLong(showing.getReservations().size()+"");

        return hash(cinemaId)+ "/" + hash(movieId) + "/" + hash(beginsAtLong) + "/" + hash(number);
    }


}
