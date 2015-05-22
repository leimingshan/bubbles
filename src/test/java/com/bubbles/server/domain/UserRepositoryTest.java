package com.bubbles.server.domain;

import com.bubbles.server.BubblesApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.junit.Assert.assertEquals;

/**
 * Created by LMSH on 2015/5/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BubblesApplication.class)
@WebAppConfiguration
@Transactional
@Sql("/test-user-data.sql")
public class UserRepositoryTest {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private UserRepository userRepository;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void testUserResr() throws Exception {
        // test spring-data-rest
        this.mockMvc.perform(get("/users-rest/10000")
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.gender").value("m"));
    }

    @Test
    public void testSetScore() {
        userRepository.setScoreById(10000L, 500);
        User user = userRepository.findOne(10000L);
        assertEquals(500, user.getScore());
    }

    @Test
    public void testSetNickname() {
        userRepository.setNicknameById(10001L, "test-nickname");
        User user = userRepository.findOne(10001L);
        assertEquals("test-nickname", user.getNickname());
    }

    @Test
    public void testSetGender() {
        userRepository.setGenderById(10000L, "m");
        User user = userRepository.findOne(10000L);
        assertEquals("m", user.getGender());
    }
}