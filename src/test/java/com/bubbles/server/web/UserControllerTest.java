package com.bubbles.server.web;

import com.bubbles.server.BubblesApplication;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
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

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test for User web controller.
 *
 * @author Mingshan Lei
 * @since 2015/5/12
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BubblesApplication.class)
@WebAppConfiguration
@Transactional
@Sql({"/test-schema.sql", "/test-user-data.sql"})
public class UserControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void testGetUserById() throws Exception {
        this.mockMvc.perform(get("/users/10000")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(jsonPath("$.deviceId").value("000000000010000"));
    }

    @Test
    public void testGetUserByDeviceId() throws Exception {
        this.mockMvc.perform(get("/users/search/deviceId/000000000010000")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(jsonPath("$.deviceId").value("000000000010000"));

        this.mockMvc.perform(get("/users/search/deviceId/000000000010005")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGetUserByAndroidId() throws Exception {
        this.mockMvc.perform(get("/users/search/androidId/12345678912345678912")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(jsonPath("$.id").value(10000));

        this.mockMvc.perform(get("/users/search/androidId/0")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGetUserStatsById() throws Exception {
        this.mockMvc.perform(get("/users/10000/stats")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(jsonPath("$.id").value(10000));

        this.mockMvc.perform(get("/users/0/stats")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testUpdateScore() throws Exception {
        this.mockMvc.perform(patch("/users/10000/score")
            .param("score", "100")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(content().string("1"));

        this.mockMvc.perform(patch("/users/0/score")
            .param("score", "100")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testAddScore() throws Exception {
        this.mockMvc.perform(post("/users/10000/score")
            .param("add", "1")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(content().string("1"));

        this.mockMvc.perform(post("/users/0/score")
            .param("add", "1")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testUpdateNickname() throws Exception {
        this.mockMvc.perform(post("/users/10000/nickname")
            .param("nickname", "jack")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(content().string("1"));

        this.mockMvc.perform(post("/users/0/nickname")
            .param("nickname", "jack")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        this.mockMvc.perform(post("/users/10001/nickname")
            .param("nickname", "this_nickname_is_too_long_this_nickname_is_too_long")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isUnprocessableEntity())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testUpdateGender() throws Exception {
        this.mockMvc.perform(post("/users/10000/gender")
            .param("gender", "f")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(content().string("1"));

        this.mockMvc.perform(post("/users/0/gender")
            .param("gender", "f")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        this.mockMvc.perform(post("/users/10001/gender")
            .param("gender", "female")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isUnprocessableEntity())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testSaveUser() throws Exception {

    }

    @Test
    public void testGetBubblesByUserId() throws Exception {
        this.mockMvc.perform(get("/users/10000/bubbles")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(jsonPath("$", hasSize(2)));

        this.mockMvc.perform(get("/users/10001/bubbles")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void testGetParticipatedBubblesByUserId() throws Exception {
        this.mockMvc.perform(get("/users/10000/participated")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void testSaveBubble() throws Exception {

    }

    @Test
    public void testSaveReply() throws Exception {

    }
}
