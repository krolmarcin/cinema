package pl.com.bottega.cms.model;

import java.time.LocalDateTime;

/**
 * Created by ogurekk on 2017-04-22.
 */
public interface ReservationNumberGenerator {

    public String generate(Showing showing);
}
