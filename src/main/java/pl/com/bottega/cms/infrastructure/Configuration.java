package pl.com.bottega.cms.infrastructure;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import pl.com.bottega.cms.application.AdminPanel;
import pl.com.bottega.cms.application.implementation.StandardAdminPanel;

/**
 * Created by ogurekk on 2017-04-09.
 */
@org.springframework.context.annotation.Configuration
@EnableAsync
public class Configuration {

    @Bean
    public AdminPanel adminPanel() {
        return new StandardAdminPanel();
    }
}
