package pl.com.bottega.cms.ui;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.com.bottega.cms.model.CinemaNotFoundException;

@ControllerAdvice
public class ErrorHandlers {

    @ExceptionHandler(CinemaNotFoundException.class)
    public ResponseEntity<String> handleCinemaNotFoundException(CinemaNotFoundException ex) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");
        return new ResponseEntity<String>(
                String.format("{\"error\": \"%s\"}", ex.getMessage()),
                headers,
                HttpStatus.UNPROCESSABLE_ENTITY
        );
    }

}
