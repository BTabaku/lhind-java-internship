package com.lhind.internshipfinalproject.controller;

import com.lhind.internshipfinalproject.dto.ApplicationDTO;
import com.lhind.internshipfinalproject.dto.JobDTO;
import com.lhind.internshipfinalproject.dto.ReviewDto;
import com.lhind.internshipfinalproject.entity.Job;
import com.lhind.internshipfinalproject.entity.User;
import com.lhind.internshipfinalproject.enums.ApplicationStatus;
import com.lhind.internshipfinalproject.mapper.JobMapper;
import com.lhind.internshipfinalproject.repository.UserRepository;
import com.lhind.internshipfinalproject.service.ApplicationService;
import com.lhind.internshipfinalproject.service.JobService;
import com.lhind.internshipfinalproject.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/employer")
@RequiredArgsConstructor
public class EmployerController {

    private final JobService jobService;
    private final JobMapper jobMapper;
    private final ApplicationService applicationService;
    private final ReviewService reviewService;
    private final UserRepository userRepository;

    @GetMapping("/jobs")
    public ResponseEntity<Page<JobDTO>> getEmployerJobs(
            @AuthenticationPrincipal org.springframework.security.core.userdetails.User principal,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String location,
            Pageable pageable) {
        // Lookup full User (employer) entity from username
        User employer = userRepository.findByUsername(principal.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Page<JobDTO> jobs = jobService.getJobsByEmployer(employer.getId(), title, location, pageable)
                .map(jobMapper::toDTO);
        return ResponseEntity.ok(jobs);
    }

    @PostMapping("/jobs")
    public ResponseEntity<JobDTO> postJob(
            @AuthenticationPrincipal org.springframework.security.core.userdetails.User principal,
            @Valid @RequestBody JobDTO jobDTO) {
        // Lookup the full User (employer) entity
        User employer = userRepository.findByUsername(principal.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
        // Set employerId in DTO so that service can use it
        jobDTO.setEmployerId(employer.getId());
        Job job = jobMapper.toEntity(jobDTO);
        Job savedJob = jobService.saveJob(job);
        return ResponseEntity.ok(jobMapper.toDTO(savedJob));
    }

    @GetMapping("/jobs/{jobId}/applications")
    public ResponseEntity<Page<ApplicationDTO>> getApplicationsForJob(
            @PathVariable Integer jobId,
            @RequestParam(required = false) ApplicationStatus status,
            Pageable pageable) {
        Page<ApplicationDTO> applications = applicationService.getApplicationsByJobAndStatus(jobId, status, pageable);
        return ResponseEntity.ok(applications);
    }

    @PutMapping("/applications/{applicationId}/status")
    public ResponseEntity<ApplicationDTO> updateApplicationStatus(
            @PathVariable Integer applicationId,
            @RequestParam ApplicationStatus status,
            @AuthenticationPrincipal org.springframework.security.core.userdetails.User principal) {
        // Lookup the full User (employer) entity
        User employer = userRepository.findByUsername(principal.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
        ApplicationDTO updatedApplication = applicationService.updateApplicationStatus(applicationId, status, employer.getId());
        return ResponseEntity.ok(updatedApplication);
    }

    // This endpoint is responsible for adding a review.
    @PostMapping("/reviews/jobs/{jobId}")
    public ResponseEntity<ReviewDto> addReview(
            @PathVariable Integer jobId,
            @RequestBody ReviewDto reviewDto,
            @AuthenticationPrincipal org.springframework.security.core.userdetails.User principal) {
        // Lookup full employer entity from username
        User employer = userRepository.findByUsername(principal.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
        ReviewDto savedReview = reviewService.addReview(jobId, reviewDto, employer);
        return ResponseEntity.ok(savedReview);
    }
}
