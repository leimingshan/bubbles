package com.bubbles.server.main;

import com.bubbles.server.BubblesApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BubblesApplication.class)
@WebAppConfiguration
public class BubblesApplicationTests {

	@Autowired
	ApplicationContext ctx;

	@Test
	public void contextLoads() {
		assertNotNull(this.ctx);
		assertTrue(this.ctx.containsBean("userController"));
		assertTrue(this.ctx.containsBean("userRepository"));
	}

}
