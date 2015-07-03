package com.bubbles.server.web;

import com.bubbles.server.domain.Bubble;
import com.bubbles.server.domain.BubbleRepository;
import com.bubbles.server.domain.User;
import com.bubbles.server.domain.UserRepository;
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

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BubbleRepository bubbleRepository;

    @Autowired
    private Validator validator;

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public User getUser(@PathVariable long userId) {
        User user = userRepository.findOne(userId);
        return user;
    }

    @RequestMapping(value = "/search/deviceId/{deviceId}", method = RequestMethod.GET)
    public User getUserByDeviceId(@PathVariable String deviceId) {
        List<User> userList = userRepository.findByDeviceId(deviceId);
        if (userList.size() != 1) {
            logger.warn("WARNING: Get more than one user by deviceId");
        }
        return userList.get(0);
    }


    @RequestMapping(value = "/{userId}/score", method = {RequestMethod.PATCH, RequestMethod.POST})  // Partially update
    public int updateScore(@PathVariable long userId, @RequestParam("score") int score) {
        if (!userRepository.exists(userId)) {
            throw new NoResourceException("Invalid User Id " + userId, null);
        }
        return userRepository.setScoreById(userId, score);
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
     * Create the user and save user info.
     *
     * @param user   user object generated from request params
     * @param errors validation result of user model in request body
     * @return id of the saved user
     */
    @RequestMapping(method = RequestMethod.POST) // create a new resource in collection
    public long saveUser(@Valid @RequestBody User user, Errors errors) {
        if (errors.hasErrors()) {
            throw new InvalidRequestException("Invalid User Entity", errors);
        }
        User userSaved = userRepository.save(user);
        return userSaved.getId();
    }

    // bubbles operations below

    /**
     * Find all bubbles posted by the user.
     *
     * @param userId user's id
     * @return bubble list. {@code null} if no bubbles found for the user.
     */
    @RequestMapping(value = "/{userId}/bubbles")
    public List<Bubble> getBubblesByUserId(@PathVariable long userId) {
        return bubbleRepository.findByUserIdOrderByTime(userId);
    }

    /**
     * Save the new bubble posted by the user.
     *
     * @param userId the user's id who posted the bubble
     * @param bubble converted bubble object from request body
     * @return the saved bubble object
     */
    @RequestMapping(value = "/{userId}/bubbles", method = RequestMethod.POST)
    public long saveBubble(@PathVariable long userId, @RequestBody Bubble bubble) {
        User user = userRepository.findOne(userId);
        if (user == null) {
            throw new NoResourceException("Invalid User Id " + userId, null);
        }
        bubble.setUser(user);
        bubble.setTimestamp(new Date());
        bubble.setLastReplyTime(new Date());
        Bubble bubbleSaved = bubbleRepository.save(bubble);
        return bubbleSaved.getId();
    }

}
