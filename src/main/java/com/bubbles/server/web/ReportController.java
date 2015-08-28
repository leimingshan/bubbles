package com.bubbles.server.web;

import com.bubbles.server.domain.Report;
import com.bubbles.server.domain.ReportRepository;
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
 * Rest controller for report resources.
 *
 * @author Mingshan Lei
 * @since 2015/8/28
 */
@RestController
@RequestMapping("/report")
public class ReportController {

    private static final int pageSize = 20; // default page size of report

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
     * Get report list in pages.
     *
     * @param pageable page and size info
     * @return result of report list
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<Report> getReportByPage(@PageableDefault(page = 0, size = pageSize) Pageable pageable) {
        return reportRepository.findByPage(pageable);
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
