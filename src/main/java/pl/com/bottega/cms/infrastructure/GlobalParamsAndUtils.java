package pl.com.bottega.cms.infrastructure;//tu bedziemy przechowywac statyczne metody/pola finalowe, z ktorych bedzie korzystac aplikacja

import java.time.format.DateTimeFormatter;

/**
 * Created by ogurekk on 2017-04-09.
 */
public class GlobalParamsAndUtils {

    public static final DateTimeFormatter LOCAL_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");

    public static final DateTimeFormatter STANDARD_LOCAL_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static final DateTimeFormatter LOCAL_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd");

    public static final DateTimeFormatter LOCAL_TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

}
