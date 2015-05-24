package com.bubbles.server.web;

import com.bubbles.server.domain.Bubble;
import com.bubbles.server.domain.BubbleRepository;
import com.bubbles.server.domain.User;
import com.bubbles.server.domain.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

/**
 * Created by LMSH on 2015/5/11.
 */
@RestController
@RequestMapping("/users")
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

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
            logger.error("ERROR: Get more than one user by deviceId");
            return null;
        }
        return userList.get(0);
    }

    @RequestMapping(value = "/{userId}/avatar", method = {RequestMethod.PATCH, RequestMethod.POST})  // Partially update
    public int uploadAvatar(@PathVariable long userId, @RequestParam("avatar") String avatarBase64String) {
        if (!userRepository.exists(userId)) {
            return 0;
        }
        // byte[] is encoded to BASE64 String in http transition
        // decode the String first
        ObjectMapper mapper = new ObjectMapper();
        byte[] avatar = mapper.convertValue(avatarBase64String, byte[].class);
        return userRepository.setAvatarById(userId, avatar);
    }

    @RequestMapping(value = "/{userId}/score", method = {RequestMethod.PATCH, RequestMethod.POST})  // Partially update
    public int updateScore(@PathVariable long userId, @RequestParam("score") int score) {
        if (!userRepository.exists(userId)) {
            return 0;
        }
        return userRepository.setScoreById(userId, score);
    }

    @RequestMapping(value = "/{userId}/nickname", method = {RequestMethod.PATCH, RequestMethod.POST})
    public int updateNickname(@PathVariable long userId, @RequestParam("nickname") String nickname) {
        if (!userRepository.exists(userId)) {
            return 0;
        }
        if (nickname.length() > 32) {
            return 0;
        }
        return userRepository.setNicknameById(userId, nickname);
    }

    @RequestMapping(value = "/{userId}/gender", method = {RequestMethod.PATCH, RequestMethod.POST})  // Partially update
    public int updateGender(@PathVariable long userId, @RequestParam("gender") String gender) {
        if (!userRepository.exists(userId)) {
            return 0;
        }
        if (gender.length() != 1) {
            return 0;
        }
        // Set<ConstraintViolation<User>> set = validator.validateValue(User.class, "gender", gender);
        return userRepository.setGenderById(userId, gender);
    }

    // HTTP method PUT update a resource -- all update

    @RequestMapping(method = RequestMethod.POST) // create a new resource in collection
    public long saveUser(@Valid @ModelAttribute("user") User user, BindingResult result) {
        if (result.hasErrors()) {
            //logger.error("Binding error");
            if (validator == null)
                logger.error("yyy");
            return 0;
        }
        User userSaved = userRepository.save(user);
        return userSaved.getId();
    }

    // bubbles operation

    // search for bubbles of user
    @RequestMapping(value = "/{userId}/bubbles")
    public List<Bubble> getBubblesByUserId(@PathVariable long userId) {
        return bubbleRepository.findByUserIdOrderByTimestamp(userId);
    }

    @RequestMapping(value = "/{userId}/bubbles", method = RequestMethod.POST)
    public Bubble saveBubble(@PathVariable long userId, @ModelAttribute("bubble") Bubble bubble) {
        User user = userRepository.findOne(userId);
        bubble.setUser(user);
        return bubbleRepository.save(bubble);
    }

}
