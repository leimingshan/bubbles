package com.bubbles.server.web;

import com.bubbles.server.domain.User;
import com.bubbles.server.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * Created by LMSH on 2015/5/11.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value="/{userId}", method = RequestMethod.GET)
    public User user(@PathVariable long userId) {
        User user = userRepository.findOne(userId);
        return user;
    }

    @RequestMapping(value="/deviceId/{deviceId}", method = RequestMethod.GET)
    public User userByDeviceId(@PathVariable String deviceId) {
        User user = userRepository.findByDeviceId(deviceId);
        return user;
    }

    @RequestMapping(value="/deviceId/avatar", method=RequestMethod.POST)
    public int uploadAvatar(@ModelAttribute User user, BindingResult result)
    {
        if (result.hasErrors()) {
            System.out.println(result.toString());
        }
        if (user != null) {
            int a = userRepository.setAvatarByDeviceId(getBytes(1), user.getDeviceId());
            return  a;
        }
        return 0;
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
