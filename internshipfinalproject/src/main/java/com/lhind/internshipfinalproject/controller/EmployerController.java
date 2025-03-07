package com.lhind.internshipfinalproject.controller;

import com.lhind.internshipfinalproject.dto.ApplicationDTO;
import com.lhind.internshipfinalproject.dto.JobDTO;
import com.lhind.internshipfinalproject.dto.ReviewDto;
import com.lhind.internshipfinalproject.enums.ApplicationStatus;
import com.lhind.internshipfinalproject.mapper.JobMapper;
import com.lhind.internshipfinalproject.service.ApplicationService;
import com.lhind.internshipfinalproject.service.JobService;
import com.lhind.internshipfinalproject.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/employer")
@RequiredArgsConstructor
public class EmployerController {

    private final JobService jobService;
    private final JobMapper jobMapper;
    private final ApplicationService applicationService;
    private final ReviewService reviewService;

    // Get jobs posted by employer with filtering
    @GetMapping("/jobs")
    public Page<JobDTO> getEmployerJobs(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String location,
            Pageable pageable) {

        return jobService.getJobsByEmployer(title, location, pageable)
                .map(jobMapper::toDTO);
    }


    // Get applications for a job
    @GetMapping("/jobs/{jobId}/applications")
    public Page<ApplicationDTO> getApplicationsForJob(
            @PathVariable Integer jobId,
            @RequestParam(required = false) ApplicationStatus status,
            Pageable pageable) {
        return applicationService.getApplicationsByJobAndStatus(jobId, status, pageable);
    }

    @PutMapping("/applications/{applicationId}/status")
    public ApplicationDTO updateApplicationStatus(
            @PathVariable Integer applicationId,
            @RequestParam ApplicationStatus status) {
        return applicationService.updateApplicationStatus(applicationId, status);
    }

    @PostMapping("/jobs/{jobId}/reviews")
    public ReviewDto addReview(
            @PathVariable Integer jobId,
            @Valid @RequestBody ReviewDto reviewDto) {
        return reviewService.addReview(jobId, reviewDto);
    }



}