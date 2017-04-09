package pl.com.bottega.cms.acceptance;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.cms.application.AdminPanel;

/**
 * Created by ogurekk on 2017-04-09.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class AdminPanelTest {

    @Autowired
    private AdminPanel adminPanel;
}
