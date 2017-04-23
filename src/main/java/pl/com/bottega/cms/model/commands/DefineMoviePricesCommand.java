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

    Long movieId;

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

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }


    @Override
    public void validate(ValidationErrors errors) {
        if (priceMap == null || priceMap.size() == 0)
            errors.add("ticketPrices", "can't be empty");
        if (!priceMap.containsKey("regular"))
            errors.add("regular", "is required field");
        else if (isInvalidPriceStructure("regular"))
            errors.add("regular", "must be number of structure 999.99 and can't be less than 0");
        if (!priceMap.containsKey("student"))
            errors.add("student", "is required field");
        else if (isInvalidPriceStructure("student"))
            errors.add("student", "must be number of structure 999.99 and can't be less than 0");
        if (priceMap.containsKey("school")) {
            if (isInvalidPriceStructure("school"))
                errors.add("school", "must be number of structure 999.99 and can't be less than 0");
        }
        if (priceMap.containsKey("children")) {
            if (isInvalidPriceStructure("children"))
                errors.add("children", "must be number of structure 999.99 and can't be less than 0");
        }
    }

    Boolean isInvalidPriceStructure(String key){
        return !Pattern.matches("^\\d+\\.\\d{2}$", priceMap.get(key).toString());
    }
}
