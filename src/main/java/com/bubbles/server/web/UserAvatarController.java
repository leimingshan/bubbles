package com.bubbles.server.web;

import com.bubbles.server.domain.UserRepository;
import com.bubbles.server.service.ImageFileService;
import com.bubbles.server.web.viewmodel.NoResourceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
     * @param userId user's id
     * @return redirect to the http server to get the corresponding img file
     */
    @RequestMapping(value = "/{userId}/avatar", method = RequestMethod.GET)
    public String getUserAvatar(@PathVariable long userId) {
        String url = userRepository.findAvatarUrlById(userId);
        return "redirect:" + url;
    }


    @RequestMapping(value = "/{userId}/avatar", method = {RequestMethod.PATCH, RequestMethod.POST})  // Partially update
    public @ResponseBody int uploadAvatar(@PathVariable long userId, @RequestParam("avatar") MultipartFile file) {
        if (!userRepository.exists(userId)) {
            throw new NoResourceException("Invalid User Id " + userId, null);
        }
        String fileName = userId + ".jpg";
        // save upload file to the specific location using filename
        String avatarUrl = imageFileService.saveImgFile(file, fileName);
        return userRepository.setAvatarUrlById(userId, avatarUrl);
    }
}
