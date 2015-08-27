package com.bubbles.server.web;

import com.bubbles.server.domain.Bubble;
import com.bubbles.server.domain.BubbleRepository;
import com.bubbles.server.domain.UserRepository;
import com.bubbles.server.util.GeoPosition;
import com.bubbles.server.util.GeoPositionUtils;
import com.bubbles.server.web.viewmodel.InvalidRequestException;
import com.bubbles.server.web.viewmodel.NoResourceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private UserRepository userRepository;

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
        List<Bubble> topReplies = bubbleRepository.findTop5ByParentBubbleId(bubbleId, pageable);
        for (int i = 0; i < topReplies.size(); i++) {
            Bubble reply = topReplies.get(i);
            if (reply.getScore() == 0) {
                topReplies.remove(i);
                i--;
            }
        }
        return topReplies;
    }

    @RequestMapping(value = "/brief", method = RequestMethod.GET)
    public List<Bubble> getBriefBubbles() {
        return bubbleRepository.findBriefBubbles();
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Bubble> getBubblesByLocation(@RequestParam("longitude") double longitude,
                                             @RequestParam("latitude") double latitude,
                                             @RequestParam("type") String type,
                                             @PageableDefault(page = 0, size = pageSize) Pageable pageable) {

        // validate request params
        if (longitude < -180 || longitude > 180) {
            logger.error("Invalid longitude: " + longitude);
            throw new InvalidRequestException("Invalid longitude: " + longitude, null);
        }
        if (latitude < -90 || latitude > 90) {
            logger.error("Invalid latitude: " + latitude);
            throw new InvalidRequestException("Invalid latitude: " + latitude, null);
        }

        // get location range for this location as center point
        GeoPosition northPoint = GeoPositionUtils.getDestinationPosition(latitude, longitude, 0.0, maxDistance);
        GeoPosition eastPoint = GeoPositionUtils.getDestinationPosition(latitude, longitude, 90.0, maxDistance);
        GeoPosition southPoint = GeoPositionUtils.getDestinationPosition(latitude, longitude, 180.0, maxDistance);
        GeoPosition westPoint = GeoPositionUtils.getDestinationPosition(latitude, longitude, 270.0, maxDistance);

        double minLatitude = southPoint.getLatitude();
        double maxLatitude = northPoint.getLatitude();
        double minLongitude = westPoint.getLongitude();
        double maxLongitude = eastPoint.getLongitude();

        // use range and type to search for bubbles
        List<Bubble> bubbleList = null;
        if (type.equals("new")) {
            bubbleList = bubbleRepository.findNewBubbles(minLatitude, maxLatitude, minLongitude, maxLongitude, pageable);
        } else  if (type.equals("hot")) {
            bubbleList = bubbleRepository.findHotBubbles(minLatitude, maxLatitude, minLongitude, maxLongitude, pageable);
        } else {
            logger.error("Invalid get bubbles type: " + type);
            throw new InvalidRequestException("Invalid get bubbles type: " + type, null);
        }

        if (bubbleList == null || bubbleList.isEmpty()) {
            logger.info("No bubble found for this search!");
            return bubbleList;
        }

        // go through bubbles for bubble distance to determine whether this bubble could be seen
        for (int i = bubbleList.size() - 1; i >= 0; i--) {
            double bubbleLat = bubbleList.get(i).getLatitude();
            double bubbleLon = bubbleList.get(i).getLongitude();
            int distance = bubbleList.get(i).getDistance();
            double actualDistance = GeoPositionUtils.getDistance(latitude, longitude, bubbleLat, bubbleLon);
            if (actualDistance > distance)
                bubbleList.remove(i);
        }

        return bubbleList;
    }

    @RequestMapping(value = "/{bubbleId}/score", method = RequestMethod.POST)
    public int addBubbleScoreById(@PathVariable long bubbleId,
                                  @RequestParam("add") int addNum) {
        if (!bubbleRepository.exists(bubbleId)) {
            logger.error("Invalid Bubble Id " + bubbleId);
            throw new NoResourceException("Invalid Bubble Id " + bubbleId, null);
        }

        // add the author's score by 1 if addNum is 1
        Bubble bubble = bubbleRepository.findOne(bubbleId);
        long author = bubble.getUser().getId();
        userRepository.addScoreById(author, addNum);

        return bubbleRepository.addScoreById(bubbleId, addNum);
    }

}
