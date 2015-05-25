package com.bubbles.server.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by LMSH on 2015/5/25.
 */
@Controller
@RequestMapping("/users")
public class UserAvatarController {
    @RequestMapping(value = "/{userId}/avatar", method = RequestMethod.GET)
    public String getUserAvatar(@PathVariable long userId) {
        return "redirect:http://112.124.56.38/avatar/123.jpg";
    }
}
