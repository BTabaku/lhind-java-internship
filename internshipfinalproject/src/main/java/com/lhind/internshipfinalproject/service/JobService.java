package com.lhind.internshipfinalproject.service;

import com.lhind.internshipfinalproject.entity.Job;
import com.lhind.internshipfinalproject.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class JobService {
    private final JobRepository jobRepository;

    @Autowired
    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public Page<Job> getAllJobs(String title, String location, Pageable pageable) {
        if (title != null || location != null) {
            return jobRepository.findByTitleOrLocation(title, location, pageable);
        }
        return jobRepository.findAll(pageable);
    }
}
