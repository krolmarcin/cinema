package pl.com.bottega.cms.model.commands;

import java.math.BigDecimal;

/**
 * Created by maciek on 15.04.2017.
 */
public class CreateTicketPriceCommand implements Validatable{

    private BigDecimal regular;
    private BigDecimal student;
    private BigDecimal school;
    private BigDecimal children;
    private Long movieId;

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

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    @Override
    public void validate(ValidationErrors errors) {
        if (regular == null || regular.signum() < 0)
            errors.add("regular", "can't be blank and can't be less than 0");
        if (student == null || student.signum() < 0)
            errors.add("student", "can't be blank and can't be less than 0");
    }


}
