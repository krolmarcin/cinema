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
    private BigDecimal regular;
    private BigDecimal student;
    private BigDecimal school;
    private BigDecimal children;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "movieId")
    private Movie movie;

    TicketPrice(){}

    public TicketPrice(Movie movie, CreateTicketPriceCommand cmd){
        this.regular = cmd.getRegular();
        this.student = cmd.getStudent();
        this.school = cmd.getSchool();
        this.children = cmd.getChildren();
        this.movie = movie;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getRegular() {
        return regular;
    }

    public void setRegular(BigDecimal regular) {
        this.regular = regular;
    }

    public BigDecimal getStudent() {
        return student;
    }

    public void setStudent(BigDecimal student) {
        this.student = student;
    }

    public BigDecimal getSchool() {
        return school;
    }

    public void setSchool(BigDecimal school) {
        this.school = school;
    }

    public BigDecimal getChildren() {
        return children;
    }

    public void setChildren(BigDecimal children) {
        this.children = children;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
