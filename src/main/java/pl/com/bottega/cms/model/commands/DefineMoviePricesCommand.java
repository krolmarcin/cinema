package pl.com.bottega.cms.model.commands;

import pl.com.bottega.cms.infrastructure.validation.Validatable;

import java.math.BigDecimal;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by maciek on 22.04.2017.
 */
public class DefineMoviePricesCommand implements Validatable {

    Map<String, BigDecimal> priceMap;

    public DefineMoviePricesCommand(Map<String, BigDecimal> priceMap) {
        this.priceMap = priceMap;
    }

    public Map<String, BigDecimal> getPriceMap() {
        return priceMap;
    }

//    @JsonProperty
    public void setPriceMap(Map<String, BigDecimal> priceMap) {
        this.priceMap = priceMap;
    }


    @Override
    public void validate(Validatable.ValidationErrors priceMap) {
        if (priceMap == null || getPriceMap().size() == 0)
            priceMap.add("ticketPrices", "can't be empty");
        if (!getPriceMap().containsKey("regular"))
            priceMap.add("regular", "is required field");
        else if (!Pattern.matches("\\d+\\.\\d{2}", getPriceMap().get("regular").toString()))
            priceMap.add("regular", "must be number of structure 999.99 and can't be less than 0");
        if (!getPriceMap().containsKey("student"))
            priceMap.add("student", "is required field");
        else if (!Pattern.matches("^\\d{1,5}\\.\\d{2}$", getPriceMap().get("student").toString()))
            priceMap.add("student", "must be number of structure 999.99 and can't be less than 0");
        if (getPriceMap().containsKey("school")) {
            if (!Pattern.matches("\\d+\\.\\d{2}", getPriceMap().get("school").toString()))
                priceMap.add("school", "must be number of structure 999.99 and can't be less than 0");
        }
        if (getPriceMap().containsKey("children")) {
            if (!Pattern.matches("\\d+\\.\\d{2}", getPriceMap().get("children").toString()))
                priceMap.add("children", "must be number of structure 999.99 and can't be less than 0");
        }
    }
}
