package com.bubbles.server.domain;

import com.bubbles.server.BubblesApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by LMSH on 2015/5/22.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BubblesApplication.class)
@Transactional
public class BubbleRepositoryTest {

    @Autowired
    private BubbleRepository bubbleRepository;

    @Test
    public void testFindByUserId() {
        List<Bubble> bubbleList = bubbleRepository.findByUserId(1);
        assertEquals(bubbleList.size(), 1);
    }
}
