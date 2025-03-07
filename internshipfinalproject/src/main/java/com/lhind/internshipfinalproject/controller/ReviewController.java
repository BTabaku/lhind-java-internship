package com.lhind.internshipfinalproject.controller;

import com.lhind.internshipfinalproject.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/v1/employee/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/jobs/{jobId}/applications")
    public Page<ApplicationDTO> getApplicationsForJob(
            @PathVariable Integer jobId,
            @RequestParam(required = false) ApplicationStatus status,
            Pageable pageable) {
        return applicationService.getApplicationsByJobAndStatus(jobId, status, pageable);
    }
}
