package com.lhind.internshipfinalproject.controller;

import com.lhind.internshipfinalproject.entity.Job;
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

    private final JobService jobService;

    @GetMapping
    public Page<Job> getAllJobs(@RequestParam(required = false) String title, @RequestParam(required = false) String location, Pageable pageable) {
        return jobService.getAllJobs(title, location, pageable);
    }
}
