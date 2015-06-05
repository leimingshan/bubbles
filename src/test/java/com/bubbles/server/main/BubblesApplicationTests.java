package com.bubbles.server.main;

import com.bubbles.server.BubblesApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BubblesApplication.class)
@WebAppConfiguration
public class BubblesApplicationTests {

    @Autowired
    ApplicationContext ctx;

    @Test
    public void contextLoads() {
        assertNotNull(this.ctx);
        // test the spring context and beans
        assertTrue(this.ctx.containsBean("userController"));
        assertTrue(this.ctx.containsBean("userRepository"));
    }

}
