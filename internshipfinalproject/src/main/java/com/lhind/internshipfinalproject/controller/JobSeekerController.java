package com.lhind.internshipfinalproject.controller;

import com.lhind.internshipfinalproject.dto.ApplicationDTO;
import com.lhind.internshipfinalproject.service.ApplicationService;
import com.lhind.internshipfinalproject.service.JobService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/jobseeker")
@RequiredArgsConstructor
public class JobSeekerController {

    private final JobService jobService;
    private final ApplicationService applicationService;

    @PostMapping("/applications")
    public ApplicationDTO applyForJob(@Valid @RequestBody ApplicationDTO applicationDTO) {
        return applicationService.applyForJob(applicationDTO);
    }

    // JobSeekerController.java


}
