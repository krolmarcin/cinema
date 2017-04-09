package pl.com.bottega.cms.model;

/**
 * Created by ogurekk on 2017-04-09.
 */
public class IncompatibleInputException extends RuntimeException {

    public IncompatibleInputException(String reason) {
        super(String.format("Input of '%s' incompatible.", reason));
    }

}
