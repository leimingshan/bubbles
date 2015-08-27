package com.bubbles.server.web;

import com.bubbles.server.domain.Advice;
import com.bubbles.server.domain.AdviceRepository;
import com.bubbles.server.web.viewmodel.InvalidRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

/**
 * Rest controller for advice resources.
 *
 * @author Mingshan Lei
 * @since 2015/8/27
 */
@RestController
@RequestMapping("/advice")
public class AdviceController {

    @Autowired
    private AdviceRepository adviceRepository;

    @RequestMapping(value = "/{adviceId}", method = RequestMethod.GET)
    public Advice getAdviceById(@PathVariable long adviceId) {
        return adviceRepository.findOne(adviceId);
    }

    @RequestMapping(method = RequestMethod.POST)
    public long saveAdvice(@Valid @RequestBody Advice advice, Errors errors) {
        if (errors.hasErrors()) {
            throw new InvalidRequestException("Invalid Advice Entity", errors);
        }

        advice.setCreatedDate(new Date());
        advice.setModifiedDate(new Date());
        Advice adviceSaved = adviceRepository.save(advice);
        return adviceSaved.getId();
    }
}
