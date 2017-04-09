package pl.com.bottega.cms.infrastructure;

import pl.com.bottega.cms.application.CinemaCatalog;
import pl.com.bottega.cms.application.CinemaDto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by maciek on 09.04.2017.
 */
public class JPACinemaCatalog implements CinemaCatalog {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<CinemaDto> getCinemas() {
        return null;
    }
}
