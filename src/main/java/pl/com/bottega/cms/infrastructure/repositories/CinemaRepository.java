package pl.com.bottega.cms.infrastructure.repositories;

import pl.com.bottega.cms.model.cinema.Cinema;

/**
 * Created by maciek on 09.04.2017.
 */
public interface CinemaRepository {

    void put(Cinema c);

    Cinema get(Long id);

    boolean exists(String name, String city);

}
