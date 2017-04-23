package pl.com.bottega.cms.model.reservation;

import java.math.BigDecimal;

/**
 * Created by maciek on 23.04.2017.
 */
public class CalculationItem {

    private String kind;
    private Integer count;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
