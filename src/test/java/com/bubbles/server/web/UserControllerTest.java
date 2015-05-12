package com.bubbles.server.web;

import com.bubbles.server.BubblesApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by LMSH on 2015/5/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BubblesApplication.class)
@WebAppConfiguration
public class UserControllerTest {

    @Test
    public void testUser() throws Exception {

    }
}