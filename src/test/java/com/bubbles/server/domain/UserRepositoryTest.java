package com.bubbles.server.domain;

import com.bubbles.server.BubblesApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * User Repository test.
 * @author Mingshan Lei
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BubblesApplication.class)
@Transactional
@Sql({"/test-schema.sql", "/test-user-data.sql"})
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindByDeviceId() {
        List<User> userList = userRepository.findByDeviceId("000000000010000");
        // should only have one user in userList
        assertNotNull(userList);
        assertEquals(1, userList.size());
        assertEquals(10000, userList.get(0).getId());
    }

    @Test
    public void testFindAvatarUrlById() {
        String url = userRepository.findAvatarUrlById(10000L);
        assertNotNull(url);
        assertEquals(url, "http://112.124.56.38/avatar/10000.jpg");
    }

    @Test
    public void testSetAvatarUrlById() {
        String url = "http://112.124.56.38/avatar/11000.jpg";
        userRepository.setAvatarUrlById(10001L, url);
        User user = userRepository.findOne(10001L);
        assertNotNull(user);
        assertEquals(url, user.getAvatarUrl());
    }

    @Test
    public void testSetScore() {
        userRepository.setScoreById(10000L, 500);
        User user = userRepository.findOne(10000L);
        assertNotNull(user);
        assertEquals(500, user.getScore());
    }

    @Test
    public void testSetNickname() {
        userRepository.setNicknameById(10001L, "test-nickname");
        User user = userRepository.findOne(10001L);
        assertNotNull(user);
        assertEquals("test-nickname", user.getNickname());
    }

    @Test
    public void testSetGender() {
        userRepository.setGenderById(10000L, "m");
        User user = userRepository.findOne(10000L);
        assertNotNull(user);
        assertEquals("m", user.getGender());
    }
}