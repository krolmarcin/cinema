package pl.com.bottega.cms.model;

import pl.com.bottega.cms.model.commands.CreateCinemaCommand;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by maciek on 09.04.2017.
 */
@Entity
public class Cinema {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String city;


    Cinema() {
    }


    public Cinema(CreateCinemaCommand cmd) {
        this.name = cmd.getName();
        this.city = cmd.getCity();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (this == null || obj == null) {
            return false;
        }
        if (!(obj instanceof Cinema)) {
            return false;
        }
        Cinema other = (Cinema) obj;
        return (this.id == other.id);
    }
}
