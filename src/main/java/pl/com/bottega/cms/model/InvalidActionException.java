package pl.com.bottega.cms.model;

public class InvalidActionException extends RuntimeException {

    public InvalidActionException(String msg) {
        super(msg);
    }

}
