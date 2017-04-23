package pl.com.bottega.cms.infrastructure.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import pl.com.bottega.cms.model.commands.CommandInvalidException;
import pl.com.bottega.cms.infrastructure.validation.Validatable;

@Component
@Aspect
public class ValidationAspect {

    @Before("execution(* pl.com.bottega.cms.application..*.*(..)) " +
            "&& args(validatable)")
    public void setCinemaId(Validatable validatable) {
        Validatable.ValidationErrors errors = new Validatable.ValidationErrors();
        validatable.validate(errors);
        if (!errors.isValid())
            throw new CommandInvalidException(errors);
    }



}
