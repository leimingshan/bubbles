package com.bubbles.server.web;

import com.bubbles.server.domain.User;
import com.bubbles.server.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by LMSH on 2015/5/11.
 */
@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/user/{userId}")
    public User user(@PathVariable long userId) {
        User user = userRepository.findOne(userId);
        return user;
    }
}
