package com.bubbles.server.domain;

import com.bubbles.server.BubblesApplication;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.PageRequest;
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
    public void testFindByUserId() throws Exception {
        List<Bubble> bubbleList = bubbleRepository.findByUserId(10000);
        assertNotNull(bubbleList);
        assertEquals(2, bubbleList.size());
    }

    @Test
    public void testFindByUserIdOrderByTime() throws Exception {
        List<Bubble> bubbleList = bubbleRepository.findByUserIdOrderByTime(10000);
        assertNotNull(bubbleList);
        assertEquals(2, bubbleList.size());
    }

    @Test
    public void testFindParticipateBubblesByUserId() throws Exception {
        List<Bubble> bubbleList = bubbleRepository.findByUserIdOrderByTime(10000);
        assertNotNull(bubbleList);
        assertEquals(2, bubbleList.size());
    }

    @Test
    public void testFindRepliesByBubbleIdOrderByTime() throws Exception {
        List<Bubble> bubbleList = bubbleRepository.findRepliesByBubbleIdOrderByTime(10000L);
        assertNotNull(bubbleList);
        assertEquals(1, bubbleList.size());
    }

    @Test
    public void testFindTop5ByParentBubbleId() throws Exception {
        List<Bubble> bubbleList = bubbleRepository.findTop5ByParentBubbleId(10000L, new PageRequest(0, 10));
        assertNotNull(bubbleList);
        assertEquals(1, bubbleList.size());
    }

    @Test
    public void testFindHotBubbles() throws Exception {
        List<Bubble> bubbleList = bubbleRepository.findHotBubbles(50.0, 52.0, 40.0, 41.0, new PageRequest(0, 10));
        assertNotNull(bubbleList);
        assertEquals(1, bubbleList.size());
    }

    @Test
    public void testFindNewBubbles() throws Exception {
        List<Bubble> bubbleList = bubbleRepository.findNewBubbles(50.0, 52.0, 40.0, 41.0, new PageRequest(0, 10));
        assertNotNull(bubbleList);
        assertEquals(1, bubbleList.size());
    }

    @Test
    public void testFindBriefBubbles() throws Exception {
        List<Bubble> bubbleList = bubbleRepository.findBriefBubbles();
        assertNotNull(bubbleList);
        assertEquals(2, bubbleList.size());
    }

    @Test
    public void testAddScoreById() throws Exception {
        int result = bubbleRepository.addScoreById(10000, 10);
        assertEquals(result, 1);
    }
}
