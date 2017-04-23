package pl.com.bottega.cms.application.catalogs;

import pl.com.bottega.cms.application.queries.ReservationQuery;
import pl.com.bottega.cms.application.results.ReservationSearchResult;

/**
 * Created by ogurekk on 2017-04-23.
 */
public interface ReservationCatalog {

    ReservationSearchResult search(ReservationQuery reservationQuery);
}
