package com.lhind.internshipfinalproject.service.impl;

import com.lhind.internshipfinalproject.dto.ApplicationDTO;
import com.lhind.internshipfinalproject.entity.Application;
import com.lhind.internshipfinalproject.entity.Job;
import com.lhind.internshipfinalproject.entity.User;
import com.lhind.internshipfinalproject.enums.ApplicationStatus;
import com.lhind.internshipfinalproject.mapper.ApplicationMapper;
import com.lhind.internshipfinalproject.repository.ApplicationRepository;
import com.lhind.internshipfinalproject.repository.JobRepository;
import com.lhind.internshipfinalproject.repository.UserRepository;
import com.lhind.internshipfinalproject.service.ApplicationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final JobRepository jobRepository;
    private final UserRepository userRepository;
    private final ApplicationMapper applicationMapper;

    public ApplicationServiceImpl(ApplicationRepository applicationRepository,
                                  JobRepository jobRepository,
                                  UserRepository userRepository,
                                  ApplicationMapper applicationMapper) {
        this.applicationRepository = applicationRepository;
        this.jobRepository = jobRepository;
        this.userRepository = userRepository;
        this.applicationMapper = applicationMapper;
    }

    @Override
    @Transactional
    public ApplicationDTO applyForJob(ApplicationDTO applicationDTO) {
        // Validate that the job exists
        Job job = jobRepository.findById(applicationDTO.getJobId())
                .orElseThrow(() -> new RuntimeException("Job not found"));
        // Validate that the job seeker exists
        User jobSeeker = userRepository.findById(applicationDTO.getJobSeekerId())
                .orElseThrow(() -> new RuntimeException("Job seeker not found"));

        Application application = new Application();
        application.setJob(job);
        application.setJobSeeker(jobSeeker);
        application.setStatus(ApplicationStatus.PENDING);

        Application savedApplication = applicationRepository.save(application);
        return applicationMapper.toDTO(savedApplication);
    }

    @Override
    @Transactional
    public ApplicationDTO updateApplicationStatus(Integer applicationId, ApplicationStatus status, Integer employerId) {
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found"));
        // Ensure the employer owns the job posting before updating
        if (!application.getJob().getEmployer().getId().equals(employerId)) {
            throw new RuntimeException("Unauthorized to update this application");
        }
        application.setStatus(status);
        return applicationMapper.toDTO(applicationRepository.save(application));
    }

    @Override
    public Page<ApplicationDTO> getApplicationsByJobSeeker(Integer jobSeekerId, Pageable pageable) {
        return applicationRepository.findByJobSeekerId(jobSeekerId, pageable)
                .map(applicationMapper::toDTO);
    }

    @Override
    public Page<ApplicationDTO> getApplicationsByJobSeekerAndStatus(Integer jobSeekerId, ApplicationStatus status, Pageable pageable) {
        return applicationRepository.findByJobSeekerIdAndStatus(jobSeekerId, status, pageable)
                .map(applicationMapper::toDTO);
    }
}
