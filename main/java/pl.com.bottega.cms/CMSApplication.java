package pl.com.bottega.dms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


/**
 * Created by ogurekk on 2017-04-09.
 */
@SpringBootApplication
public class CMSApplication {


    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(CMSApplication.class, args);


    }
}