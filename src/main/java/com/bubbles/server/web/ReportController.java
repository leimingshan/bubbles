package com.bubbles.server.web;

import com.bubbles.server.domain.Report;
import com.bubbles.server.domain.ReportRepository;
import com.bubbles.server.web.viewmodel.InvalidRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

/**
 * Rest controller for report resources.
 *
 * @author Mingshan Lei
 * @since 2015/8/28
 */
@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private ReportRepository reportRepository;

    /**
     * Get report entity by id.
     *
     * @param reportId
     * @return the report entity
     */
    @RequestMapping(value = "/{reportId}", method = RequestMethod.GET)
    public Report getReportById(@PathVariable long reportId) {
        return reportRepository.findOne(reportId);
    }

    /**
     * Create and save the advice.
     *
     * @param report advice object generated from request body
     * @param errors validation result of advice model in request body
     * @return id of the saved advice entity
     */
    @RequestMapping(method = RequestMethod.POST)
    public long saveReport(@Valid @RequestBody Report report, Errors errors) {
        if (errors.hasErrors()) {
            throw new InvalidRequestException("Invalid Report Entity", errors);
        }

        report.setCreatedDate(new Date());
        report.setModifiedDate(new Date());
        Report reportSaved = reportRepository.save(report);
        return reportSaved.getId();
    }
}
