package com.bubbles.server.web;

import com.bubbles.server.domain.User;
import com.bubbles.server.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by LMSH on 2015/5/11.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value="/{userId}", method = RequestMethod.GET)
    public User user(@PathVariable long userId) {
        User user = userRepository.findOne(userId);
        return user;
    }

    @RequestMapping(value="/deviceId/{deviceId}", method = RequestMethod.GET)
    public User userByDeviceId(@PathVariable String deviceId) {
        List<User> userList = userRepository.findByDeviceId(deviceId);
        if (userList.size() != 1) {
            logger.error("ERROR: Get more than one user by deviceId");
            return null;
        }
        return userList.get(0);
    }

    @RequestMapping(value="/{userId}/avatar", method=RequestMethod.PATCH)  // Partially update
    public int uploadAvatar(@PathVariable long userId, @RequestParam("avatar") byte[] avatar)
    {
        if (!userRepository.exists(userId)) {
            return 0;
        }
        return userRepository.setAvatarByDeviceId(getBytes(1), userId);
    }

    // PUT all update

    @RequestMapping(method=RequestMethod.POST) // new and create
    public long saveUser(@ModelAttribute("user") User user, BindingResult result)
    {
        if (result.hasErrors()) {
            logger.error("Binding error");
            return 0;
        }
        User userSaved = userRepository.save(user);
        return userSaved.getId();
    }

    private static byte[] getBytes(int data)
    {
        byte[] bytes = new byte[4];
        bytes[0] = (byte) (data & 0xff);
        bytes[1] = (byte) ((data & 0xff00) >> 8);
        bytes[2] = (byte) ((data & 0xff0000) >> 16);
        bytes[3] = (byte) ((data & 0xff000000) >> 24);
        return bytes;
    }

}
