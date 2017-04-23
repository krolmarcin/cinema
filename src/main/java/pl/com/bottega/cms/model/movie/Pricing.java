package pl.com.bottega.cms.model.movie;

import pl.com.bottega.cms.model.commands.DefineMoviePricesCommand;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Map;

/**
 * Created by maciek on 15.04.2017.
 */
@Entity
public class Pricing {

    @Id
    @GeneratedValue
    private Long id;

    @ElementCollection
    private Map<String, BigDecimal> priceMap;

    public Pricing(){}

    public Pricing(DefineMoviePricesCommand dmpc){
        this.priceMap = dmpc.getPriceMap();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Map<String, BigDecimal> getPriceMap() {
        return priceMap;
    }

    public void setPriceMap(Map<String, BigDecimal> priceMap) {
        this.priceMap = priceMap;
    }

    public void updatePrices(DefineMoviePricesCommand dmpc) {
        priceMap = dmpc.getPriceMap();
    }
}
