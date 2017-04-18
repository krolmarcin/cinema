package pl.com.bottega.cms.model;

import pl.com.bottega.cms.model.commands.CreateTicketPriceCommand;
import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by maciek on 15.04.2017.
 */
@Entity
public class TicketPrice {

    @Id
    @GeneratedValue
    private Long id;
    private String kind;
    private BigDecimal unitPrice;

    TicketPrice(){}

    public TicketPrice(String kind, BigDecimal unitPrice){
        this.kind = kind;
        this.unitPrice = unitPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }
}
