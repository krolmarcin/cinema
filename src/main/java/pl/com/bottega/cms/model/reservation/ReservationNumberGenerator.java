package pl.com.bottega.cms.model.reservation;

import pl.com.bottega.cms.model.showing.Showing;

import java.time.LocalDateTime;

/**
 * Created by ogurekk on 2017-04-22.
 */
public interface ReservationNumberGenerator {

    public String generate(Showing showing);
}
