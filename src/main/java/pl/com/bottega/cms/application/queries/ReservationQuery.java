package pl.com.bottega.cms.application.queries;

import pl.com.bottega.cms.model.reservation.ReservationStatus;

/**
 * Created by ogurekk on 2017-04-23.
 */
public class ReservationQuery {

    private String query;
    private ReservationStatus status;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }
}
