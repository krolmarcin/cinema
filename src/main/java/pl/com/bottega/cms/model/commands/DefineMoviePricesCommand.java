package pl.com.bottega.cms.model.commands;

import com.fasterxml.jackson.annotation.JsonProperty;

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
    public void validate(Validatable.ValidationErrors errors) {
        if (priceMap == null || priceMap.size() == 0)
            errors.add("ticketPrices", "can't be empty");
        if (!priceMap.containsKey("regular"))
            errors.add("regular", "is required field");
        else if (!Pattern.matches("\\d+\\.\\d{2}", priceMap.get("regular").toString()))
            errors.add("regular", "must be number of structure 999.99 and can't be less than 0");
        if (!priceMap.containsKey("student"))
            errors.add("student", "is required field");
        else if (!Pattern.matches("^\\d{1,5}\\.\\d{2}$", priceMap.get("student").toString()))
            errors.add("student", "must be number of structure 999.99 and can't be less than 0");
        if (priceMap.containsKey("school")) {
            if (!Pattern.matches("\\d+\\.\\d{2}", priceMap.get("school").toString()))
                errors.add("school", "must be number of structure 999.99 and can't be less than 0");
        }
        if (priceMap.containsKey("children")) {
            if (!Pattern.matches("\\d+\\.\\d{2}", priceMap.get("children").toString()))
                errors.add("children", "must be number of structure 999.99 and can't be less than 0");
        }
    }
}
