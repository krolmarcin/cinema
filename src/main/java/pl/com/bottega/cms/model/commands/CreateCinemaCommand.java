package pl.com.bottega.cms.model.commands;

import pl.com.bottega.cms.infrastructure.validation.Validatable;

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
        ensureNotEmpty(name, "name", errors);
        ensureNotEmpty(city, "city", errors);
    }
}
