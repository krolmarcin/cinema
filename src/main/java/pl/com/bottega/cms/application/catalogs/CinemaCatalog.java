package pl.com.bottega.cms.application.catalogs;

import pl.com.bottega.cms.application.dtos.CinemaDto;

import java.util.List;

/**
 * Created by maciek on 09.04.2017.
 */
public interface CinemaCatalog {

    List<CinemaDto> getCinemas();
}
