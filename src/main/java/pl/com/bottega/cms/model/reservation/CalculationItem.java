package pl.com.bottega.cms.model.reservation;

import java.math.BigDecimal;

/**
 * Created by maciek on 23.04.2017.
 */
public class CalculationItem {

    private String kind;
    private Long count;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;

    public CalculationItem(ReservationItem reservationItem, BigDecimal unitPrice) {
        this.kind = reservationItem.getKind();
        this.count = reservationItem.getNumber();
        this.unitPrice = unitPrice;
        this.totalPrice = unitPrice.multiply(BigDecimal.valueOf(count));
    }

    public String getKind() {
        return kind;
    }

    public Long getCount() {
        return count;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

}
