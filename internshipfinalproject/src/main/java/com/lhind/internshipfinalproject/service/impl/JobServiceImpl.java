package com.lhind.internshipfinalproject.service.impl;

import com.lhind.internshipfinalproject.entity.Job;
import com.lhind.internshipfinalproject.repository.JobRepository;
import com.lhind.internshipfinalproject.service.JobService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public Job saveJob(Job job) {
        return jobRepository.save(job);
    }

    @Override
    public Page<Job> getJobsByEmployer(Integer employerId, String title, String location, Pageable pageable) {
        if (title != null || location != null) {
            return jobRepository.findEmployerJobs(employerId, title, location, pageable);
        }
        return jobRepository.findByEmployerId(employerId, pageable);
    }

    @Override
    public Page<Job> searchJobs(String title, String location, String employer, Pageable pageable) {
        return jobRepository.searchJobs(title, location, employer, pageable);
    }
}
