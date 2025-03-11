package com.lhind.internshipfinalproject.service;

import com.lhind.internshipfinalproject.entity.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface JobService {
    Job saveJob(Job job);
    Page<Job> getJobsByEmployer(Integer employerId, String title, String location, Pageable pageable);
    Page<Job> searchJobs(String title, String location, String employer, Pageable pageable);
}
