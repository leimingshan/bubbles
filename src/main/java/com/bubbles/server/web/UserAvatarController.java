package com.bubbles.server.web;

import com.bubbles.server.domain.User;
import com.bubbles.server.domain.UserRepository;
import com.bubbles.server.service.ImageFileService;
import com.bubbles.server.web.viewmodel.NoResourceException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

/**
 * Controller for uploading and downloading avatars.
 *
 * @author Mingshan Lei
 * @since 2015/5/25
 */
@Controller
@RequestMapping("/users")
public class UserAvatarController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ImageFileService imageFileService;

    /**
     * Get the user's avatar image file.
     *
     * @param userId user's id
     * @return redirect to the http server to get the corresponding img file
     */
    @RequestMapping(value = "/{userId}/avatar", method = RequestMethod.GET)
    public String getUserAvatar(@PathVariable long userId, @RequestParam(value = "timestamp", required = false) String timestampString) {
        // String url = userRepository.findAvatarUrlById(userId);
        // return "redirect:" + url;
        User user = userRepository.findOne(userId);
        if (user == null) {
            throw new NoResourceException("Invalid User Id " + userId, null);
        }

        ObjectMapper mapper = new ObjectMapper();
        Date timestamp = null;
        if (timestampString != null && !timestampString.isEmpty()) {
            try {
                timestamp = mapper.readValue(timestampString, Date.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (timestamp == null || timestamp.before(user.getModifiedDate())) {
            return "redirect:" + user.getAvatarUrl();
        } else {
            return "redirect:/users/avatar/notmodified";
        }
    }

    /**
     * Upload and save the user's avatar file.
     *
     * @param userId
     * @param file uploaded file
     * @return 1 for success
     */
    @RequestMapping(value = "/{userId}/avatar", method = {RequestMethod.PATCH, RequestMethod.POST})
    public @ResponseBody int uploadAvatar(@PathVariable long userId, @RequestParam("avatar") MultipartFile file) {
        if (!userRepository.exists(userId)) {
            throw new NoResourceException("Invalid User Id " + userId, null);
        }
        String fileName = userId + ".jpg";
        // save upload file to the specific location using filename
        String avatarUrl = imageFileService.saveImgFile(file, fileName);
        return userRepository.setAvatarUrlById(userId, avatarUrl);
    }

    @RequestMapping(value = "/avatar/notmodified", method = RequestMethod.GET)
    public @ResponseBody int getUserAvatarNotModified() {
        return 1;
    }
}
