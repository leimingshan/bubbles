package com.bubbles.server.domain;

import com.bubbles.server.BubblesApplication;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Test for use of JPA and JPA context
 *
 * @author Mingshan Lei
 * @since 2015/5/16
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BubblesApplication.class)
@Transactional
public class JpaTest {

    @Autowired
    private EntityManagerFactory factory;

    /**
     * Test save method using JPA entity manager
     */
    @Test
    public void testSave() {
        EntityManager em = factory.createEntityManager();

        User user = new User();
        user.setDeviceId("000000000000005");
        user.setCellphone("18910334672");
        user.setNickname("helloiam");
        user.setGender("f");

        User userSaved = em.merge(user);
        assertNotNull(userSaved);
        assertEquals("helloiam", userSaved.getNickname());

        em.close();
    }
}
