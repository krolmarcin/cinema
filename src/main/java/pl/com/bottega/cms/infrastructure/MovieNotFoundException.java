package pl.com.bottega.cms.infrastructure;

/**
 * Created by ogurekk on 2017-04-09.
 */
public class MovieNotFoundException extends RuntimeException {
    public MovieNotFoundException(String message) {
        super(message);
    }
}
