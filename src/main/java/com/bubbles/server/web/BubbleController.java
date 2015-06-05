package com.bubbles.server.web;

import com.bubbles.server.domain.Bubble;
import com.bubbles.server.domain.BubbleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Rest controller for bubbles resources.
 *
 * @author Mingshan Lei
 * @since 2015/5/21
 */
@RestController
@RequestMapping("/bubbles")
public class BubbleController {
    
    private static final Logger logger = LoggerFactory.getLogger(BubbleController.class);

    @Autowired
    private BubbleRepository bubbleRepository;

    @RequestMapping(value = "/{bubbleId}", method = RequestMethod.GET)
    public Bubble getBubbleById(@PathVariable long bubbleId) {
        return bubbleRepository.findOne(bubbleId);
    }

    @RequestMapping(value = "/{bubbleId}/replies", method = RequestMethod.GET)
    public List<Bubble> getRepliesByBubbleId(@PathVariable long bubbleId) {
        return bubbleRepository.findRepliesByBubbleIdOrderByTime(bubbleId);
    }

    @RequestMapping(value = "/{bubbleId}/replies/top", method = RequestMethod.GET)
    public List<Bubble> getTop5RepliesByBubbleId(@PathVariable long bubbleId,
                                                 @PageableDefault(page = 0, size = 5, sort = {"score"}, direction = Sort.Direction.DESC) Pageable pageable) {
        return bubbleRepository.findTop5ByParentBubbleId(bubbleId, pageable);
    }
}
