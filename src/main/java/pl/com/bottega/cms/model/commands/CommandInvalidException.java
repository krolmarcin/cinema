package pl.com.bottega.cms.model.commands;

import pl.com.bottega.cms.infrastructure.validation.Validatable;

public class CommandInvalidException extends RuntimeException {

    private Validatable.ValidationErrors errors;

    public CommandInvalidException(Validatable.ValidationErrors errors) {

        this.errors = errors;
    }

    public Validatable.ValidationErrors getErrors() {
        return errors;
    }


}
