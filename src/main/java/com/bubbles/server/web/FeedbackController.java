package com.bubbles.server.web;

import com.bubbles.server.domain.Feedback;
import com.bubbles.server.domain.FeedbackRepository;
import com.bubbles.server.web.viewmodel.InvalidRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * Rest controller for feedback resources.
 *
 * @author Mingshan Lei
 * @since 2015/8/27
 */
@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    private static final int pageSize = 20; // default page size of report

    @Autowired
    private FeedbackRepository feedbackRepository;

    /**
     * Get feedback entity by id.
     *
     * @param feedbackId
     * @return the feedback entity
     */
    @RequestMapping(value = "/{feedbackId}", method = RequestMethod.GET)
    public Feedback getAdviceById(@PathVariable long feedbackId) {
        return feedbackRepository.findOne(feedbackId);
    }

    /**
     * Get feedback list in pages.
     *
     * @param pageable page and size info
     * @return result of feedback list
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<Feedback> getFeedbackByPage(@PageableDefault(page = 0, size = pageSize) Pageable pageable) {
        return feedbackRepository.findByPage(pageable);
    }

    /**
     * Create and save the feedback.
     *
     * @param feedback feedback object generated from request body
     * @param errors validation result of feedback model in request body
     * @return id of the saved feedback entity
     */
    @RequestMapping(method = RequestMethod.POST)
    public long saveAdvice(@Valid @RequestBody Feedback feedback, Errors errors) {
        if (errors.hasErrors()) {
            throw new InvalidRequestException("Invalid Feedback Entity", errors);
        }

        feedback.setCreatedDate(new Date());
        feedback.setModifiedDate(new Date());
        Feedback feedbackSaved = feedbackRepository.save(feedback);
        return feedbackSaved.getId();
    }
}
