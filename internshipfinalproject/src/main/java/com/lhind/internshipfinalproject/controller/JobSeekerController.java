package com.lhind.internshipfinalproject.controller;

import com.lhind.internshipfinalproject.dto.ApplicationDTO;
import com.lhind.internshipfinalproject.enums.ApplicationStatus;
import com.lhind.internshipfinalproject.mapper.JobMapper;
import com.lhind.internshipfinalproject.service.ApplicationService;
import com.lhind.internshipfinalproject.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/jobseeker")
@RequiredArgsConstructor
public class JobSeekerController {
    private final ApplicationService applicationService;
    private final JobService jobService;
    private final JobMapper jobMapper;

    @GetMapping("/applications")
    public Page<ApplicationDTO> getMyApplications(
            @RequestParam Integer jobSeekerId, // Should come from authentication
            @RequestParam(required = false) ApplicationStatus status,
            Pageable pageable
    ) {
        if (status != null) {
            return applicationService.getApplicationsByJobSeekerAndStatus(jobSeekerId, status, pageable);
        }
        return applicationService.getApplicationsByJobSeeker(jobSeekerId, pageable);
    }
}
