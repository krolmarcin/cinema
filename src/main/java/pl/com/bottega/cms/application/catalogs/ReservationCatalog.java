package pl.com.bottega.cms.application.catalogs;

import pl.com.bottega.cms.application.queries.ReservationQuery;
import pl.com.bottega.cms.application.results.ReservationSearchResult;

import java.util.List;

/**
 * Created by ogurekk on 2017-04-23.
 */
public interface ReservationCatalog {

    List<ReservationSearchResult> search(ReservationQuery reservationQuery);
}
