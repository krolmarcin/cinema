package pl.com.bottega.cms.model.commands;

import static pl.com.bottega.cms.model.commands.ValidationError.REQUIRED;

/**
 * Created by maciek on 09.04.2017.
 */
public class CreateCinemaCommand implements Validatable {
    public String name;
    public String city;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public void validate(ValidationErrors errors) {
        if (isEmpty(name))
            errors.add("name", REQUIRED.getValMsg());
        if (isEmpty(city))
            errors.add("city", REQUIRED.getValMsg());
    }
}
