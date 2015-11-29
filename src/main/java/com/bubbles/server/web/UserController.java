package com.bubbles.server.web;

import com.bubbles.server.domain.*;
import com.bubbles.server.util.ValidationUtils;
import com.bubbles.server.web.viewmodel.InvalidRequestException;
import com.bubbles.server.web.viewmodel.NoResourceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validator;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Rest controller for users resources.
 *
 * @author Mingshan Lei
 * @since 2015/5/11
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private static final int bubbleScore = 10; // add the author's score by 10 if he posted one new bubble

    private static final int getReplyScore = 2; // get one new reply, add score by 2

    private static final int postReplyScore = 2; // post one new reply, add score by 2

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BubbleRepository bubbleRepository;

    @Autowired
    private Validator validator;

    /**
     * Get user entity by id.
     *
     * @param userId the user's id to search for
     * @return the user entity found
     */
    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public User getUserById(@PathVariable long userId) {
        User user = userRepository.findOne(userId);
        return user;
    }

    /**
     * Get user entity by deviceId.
     *
     * @param deviceId the user's deviceId to search for
     * @return the user entity found
     */
    @RequestMapping(value = "/search/deviceId/{deviceId}", method = RequestMethod.GET)
    public User getUserByDeviceId(@PathVariable String deviceId) {
        List<User> userList = userRepository.findByDeviceId(deviceId);
        if (userList == null || userList.isEmpty()) {
            logger.error("Invalid device Id " + deviceId);
            throw new NoResourceException("Invalid device Id " + deviceId, null);
        }
        if (userList.size() != 1) {
            logger.warn("WARNING: Get more than one user by deviceId");
        }
        return userList.get(0);
    }
    
    /**
     * Get user entity by androidId.
     *
     * @param androidId the user's androidId to search for
     * @return the user entity found
     */
    @RequestMapping(value = "/search/androidId/{androidId}", method = RequestMethod.GET)
    public User getUserByAndroidId(@PathVariable String androidId) {
        List<User> userList = userRepository.findByAndroidId(androidId);
        if (userList == null || userList.isEmpty()) {
            logger.error("Invalid android Id " + androidId);
            throw new NoResourceException("Invalid android Id " + androidId, null);
        }
        if (userList.size() != 1) {
            logger.warn("WARNING: Get more than one user by androidId");
        }
        return userList.get(0);
    }

    /**
     * Get user statistics of scores by userId.
     *
     * @param userId the user's id
     * @return the user stats entity
     */
    @RequestMapping(value = "/{userId}/stats", method = RequestMethod.GET)
    public UserStats getUserStatsById(@PathVariable long userId) {
        UserStats userStats = new UserStats();
        if (!userRepository.exists(userId)) {
            throw new NoResourceException("Invalid User Id " + userId, null);
        }

        User currentUser = userRepository.findOne(userId);

        // search for user stats info
        userStats.setId(userId);
        userStats.setScore(currentUser.getScore());

        // use sql to calculate real-time statistics
        userStats.setPostBubblesCount(userRepository.findPostBubblesCountById(userId));
        userStats.setPostRepliesCount(userRepository.findPostRepliesCountById(userId));
        userStats.setGetRepliesCount(userRepository.findGetRepliesCountById(userId));
        userStats.setApprovalCount(userRepository.findApprovalCountById(userId));

        return userStats;
    }

    @RequestMapping(value = "/{userId}/score", method = {RequestMethod.PATCH})  // Partially update
    public int updateScore(@PathVariable long userId, @RequestParam("score") int score) {
        if (!userRepository.exists(userId)) {
            throw new NoResourceException("Invalid User Id " + userId, null);
        }
        return userRepository.setScoreById(userId, score);
    }

    @RequestMapping(value = "/{userId}/score", method = {RequestMethod.POST})
    public int addScore(@PathVariable long userId, @RequestParam("add") int addNum) {
        if (!userRepository.exists(userId)) {
            throw new NoResourceException("Invalid User Id " + userId, null);
        }
        return userRepository.addScoreById(userId, addNum);
    }

    @RequestMapping(value = "/{userId}/nickname", method = {RequestMethod.PATCH, RequestMethod.POST})
    public int updateNickname(@PathVariable long userId, @RequestParam("nickname") String nickname) {
        if (!userRepository.exists(userId)) {
            throw new NoResourceException("Invalid User Id " + userId, null);
        }
        if (nickname.length() > 32) {
            throw new InvalidRequestException("Invalid nickname " + nickname, null);
        }
        return userRepository.setNicknameById(userId, nickname);
    }

    @RequestMapping(value = "/{userId}/gender", method = {RequestMethod.PATCH, RequestMethod.POST})  // Partially update
    public int updateGender(@PathVariable long userId, @RequestParam("gender") String gender) {
        if (!userRepository.exists(userId)) {
            throw new NoResourceException("Invalid User Id " + userId, null);
        }

        Set<ConstraintViolation<User>> set = validator.validateValue(User.class, "gender", gender);
        Errors result = ValidationUtils.convertToErrors(set, new User(), "user");
        if (result.hasErrors()) {
            throw new InvalidRequestException("Invalid User Entity", result);
        }
        return userRepository.setGenderById(userId, gender);
    }

    // HTTP method PUT update a resource -- all update

    /**
     * Create the user and save the user entity.
     *
     * @param user   user object generated from request body
     * @param errors validation result of user model in request body
     * @return id of the saved user entity
     */
    @RequestMapping(method = RequestMethod.POST) // create a new resource in collection
    public long saveUser(@Valid @RequestBody User user, Errors errors) {
        if (errors.hasErrors()) {
            throw new InvalidRequestException("Invalid User Entity", errors);
        }
        user.setCreatedDate(new Date());
        user.setModifiedDate(new Date());
        User userSaved = userRepository.save(user);
        return userSaved.getId();
    }

    // bubbles operations below
    // get and post bubbles or replies

    /**
     * Find all bubbles posted by the user.
     *
     * @param userId user's id
     * @return bubble list. {@code null} if no bubbles found for the user.
     */
    @RequestMapping(value = "/{userId}/bubbles", method = RequestMethod.GET)
    public List<Bubble> getBubblesByUserId(@PathVariable long userId) {
        return bubbleRepository.findByUserIdOrderByTime(userId);
    }

    @RequestMapping(value = "/{userId}/participated", method = RequestMethod.GET)
    public List<Bubble> getParticipatedBubblesByUserId(@PathVariable long userId) {
        return bubbleRepository.findParticipateBubblesByUserId(userId);
    }

    /**
     * Save new bubble posted by the user.
     *
     * @param userId the user's id who posted the bubble
     * @param bubble converted bubble entity from request body
     * @return id of the saved bubble entity
     */
    @RequestMapping(value = "/{userId}/bubbles", method = RequestMethod.POST)
    public long saveBubble(@PathVariable long userId, @RequestBody Bubble bubble) {
        User user = userRepository.findOne(userId);
        if (user == null) {
            throw new NoResourceException("Invalid User Id " + userId, null);
        }

        // validate bubble properties
        if (bubble.getLatitude() == null || bubble.getLongitude() == null || bubble.getDistance() == null) {
            throw new InvalidRequestException("Invalid bubble properties", null);
        }

        bubble.setUser(user);
        bubble.setTimestamp(new Date());
        bubble.setLastReplyTime(new Date());
        Bubble bubbleSaved = bubbleRepository.save(bubble);

        // add the author's score by 10 if he posted one new bubble
        userRepository.addScoreById(userId, bubbleScore);

        return bubbleSaved.getId();
    }

    /**
     * Save new reply posted by the user.
     *
     * @param userId the user's id who posted the reply
     * @param bubble converted bubble entity from request body
     * @return id of the save bubble entity
     */
    @RequestMapping(value = "/{userId}/replies", method = RequestMethod.POST)
    public long saveReply(@PathVariable long userId, @RequestBody Bubble bubble) {
        User user = userRepository.findOne(userId);
        if (user == null) {
            throw new NoResourceException("Invalid User Id " + userId, null);
        }

        if (bubble.getParentBubbleId() == null) {
            throw  new InvalidRequestException("Invalid reply: no parent bubble id", null);
        }

        bubble.setUser(user);
        bubble.setTimestamp(new Date());
        bubble.setLastReplyTime(new Date());
        Bubble bubbleSaved = bubbleRepository.save(bubble);

        // set parent bubble reply time
        Bubble parentBubble = bubbleRepository.findOne(bubble.getParentBubbleId());
        parentBubble.setLastReplyTime(new Date());
        parentBubble.setReplyNum(parentBubble.getReplyNum() + 1);
        bubbleRepository.save(parentBubble);

        if (bubble.getParentId() != null) {
            Bubble parent = bubbleRepository.findOne(bubble.getParentId());
            parent.setLastReplyTime(new Date());
            parent.setReplyNum(parent.getReplyNum() + 1);
            bubbleRepository.save(parent);
        }

        // add the replied to bubble's author's score by 2
        long parentBubbleAuthorId = parentBubble.getUser().getId();
        userRepository.addScoreById(parentBubbleAuthorId, getReplyScore);

        // add the score by 2 when posted one new reply
        userRepository.addScoreById(userId, postReplyScore);

        return bubbleSaved.getId();
    }

}
