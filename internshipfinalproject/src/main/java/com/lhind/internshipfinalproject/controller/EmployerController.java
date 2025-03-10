package com.lhind.internshipfinalproject.controller;

import com.lhind.internshipfinalproject.dto.ApplicationDTO;
import com.lhind.internshipfinalproject.dto.JobDTO;
import com.lhind.internshipfinalproject.entity.Job;
import com.lhind.internshipfinalproject.entity.User;
import com.lhind.internshipfinalproject.enums.ApplicationStatus;
import com.lhind.internshipfinalproject.mapper.JobMapper;
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

    @GetMapping("/jobs")
    public ResponseEntity<Page<JobDTO>> getEmployerJobs(
            @AuthenticationPrincipal User user,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String location,
            Pageable pageable) {
        Page<JobDTO> jobs = jobService.getJobsByEmployer(user.getId(), title, location, pageable)
                .map(jobMapper::toDTO);
        return ResponseEntity.ok(jobs);
    }

    @GetMapping("/jobs/{jobId}/applications")
    public ResponseEntity<Page<ApplicationDTO>> getApplicationsForJob(
            @PathVariable Integer jobId,
            @RequestParam(required = false) ApplicationStatus status,
            Pageable pageable) {
        Page<ApplicationDTO> applications = applicationService.getApplicationsByJobAndStatus(jobId, status, pageable);
        return ResponseEntity.ok(applications);
    }

    @PostMapping("/jobs")
    public ResponseEntity<JobDTO> postJob(
            @AuthenticationPrincipal User user,
            @Valid @RequestBody JobDTO jobDTO) {
        jobDTO.setEmployerId(user.getId());
        Job job = jobMapper.toEntity(jobDTO);
        Job savedJob = jobService.saveJob(job);
        return ResponseEntity.ok(jobMapper.toDTO(savedJob));
    }

    @PutMapping("/applications/{applicationId}/status")
    public ResponseEntity<ApplicationDTO> updateApplicationStatus(
            @PathVariable Integer applicationId,
            @RequestParam ApplicationStatus status,
            @AuthenticationPrincipal User user) {
        ApplicationDTO updatedApplication = applicationService.updateApplicationStatus(applicationId, status, user.getId());
        return ResponseEntity.ok(updatedApplication);
    }
}