package pl.com.bottega.cms.ui;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.com.bottega.cms.infrastructure.repositories.EntityNotFoundException;
import pl.com.bottega.cms.model.commands.CommandInvalidException;
import pl.com.bottega.cms.infrastructure.validation.Validatable;

@ControllerAdvice
public class ErrorHandlers {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException ex) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");
        return new ResponseEntity<String>(
                String.format("{\"error\": \"%s\"}", ex.getMessage()),
                headers,
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(CommandInvalidException.class)
    public ResponseEntity<Validatable.ValidationErrors> handleCommandInvalidException(CommandInvalidException ex) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");
        return new ResponseEntity<Validatable.ValidationErrors>(
                ex.getErrors(),
                headers,
                HttpStatus.UNPROCESSABLE_ENTITY
        );
    }

    @ExceptionHandler(InvalidActionException.class)
    public ResponseEntity<String> handleInvalidActionException(InvalidActionException ex) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");
        return new ResponseEntity<String>(
                String.format("{\"error\": \"%s\"}", ex.getMessage()),
                headers,
                HttpStatus.UNPROCESSABLE_ENTITY
        );
    }

}
