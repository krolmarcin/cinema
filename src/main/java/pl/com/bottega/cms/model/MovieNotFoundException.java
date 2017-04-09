package pl.com.bottega.cms.model;

public class MovieNotFoundException extends RuntimeException {

    public MovieNotFoundException(String msg) {
        super(msg);
    }

}
