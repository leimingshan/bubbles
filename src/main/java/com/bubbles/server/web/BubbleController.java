package com.bubbles.server.web;

import com.bubbles.server.domain.Bubble;
import com.bubbles.server.domain.BubbleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by LMSH on 2015/5/21.
 */
@RestController
@RequestMapping("/bubbles")
public class BubbleController {
    Logger logger = LoggerFactory.getLogger(BubbleController.class);

    @Autowired
    private BubbleRepository bubbleRepository;

    @RequestMapping(value = "/{bubbleId}", method = RequestMethod.GET)
    public Bubble getBubbleById(@PathVariable long bubbleId) {
        return bubbleRepository.findOne(bubbleId);
    }
}
