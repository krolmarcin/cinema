package pl.com.bottega.cms.model.commands;

/**
 * Created by maciek on 09.04.2017.
 */
public class CreateCinemaCommand {
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
            errors.add("name", "can't be blank");
        if (isEmpty(city))
            errors.add("city", "can't be blank");
    }
}
