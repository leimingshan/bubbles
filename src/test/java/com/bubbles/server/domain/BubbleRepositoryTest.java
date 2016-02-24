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
 * Test for Bubble Repository.
 *
 * @author Mingshan Lei
 * @since 2015/5/16
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BubblesApplication.class)
@Transactional
@Sql({"/test-schema.sql", "/test-user-data.sql"})
public class BubbleRepositoryTest {

    @Autowired
    private BubbleRepository bubbleRepository;

    @Test
    public void testFindByUserId() {
        List<Bubble> bubbleList = bubbleRepository.findByUserId(10000);
        assertNotNull(bubbleList);
        assertEquals(2, bubbleList.size());
    }
}
