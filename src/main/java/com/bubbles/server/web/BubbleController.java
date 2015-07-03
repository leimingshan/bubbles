package com.bubbles.server.web;

import com.bubbles.server.domain.Bubble;
import com.bubbles.server.domain.BubbleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    private static final int pageSize = 20; // default page size of bubbles results

    private static final int maxDistance = 2000; // 2000 meters for bubbles maximum search radius

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
                                                 @PageableDefault(page = 0, size = pageSize, sort = {"score"}, direction = Sort.Direction.DESC) Pageable pageable) {
        return bubbleRepository.findTop5ByParentBubbleId(bubbleId, pageable);
    }


    @RequestMapping(method = RequestMethod.GET)
    public List<Bubble> getBubblesByLocation(@RequestParam("longitude") double longitude,
                                             @RequestParam("latitude") double latitude,
                                             @RequestParam("offset") int offset,
                                             @RequestParam("type") String type) {

        // TODO
        List<Bubble> bubbleList = new ArrayList<Bubble>();
        return bubbleList;
    }

}
