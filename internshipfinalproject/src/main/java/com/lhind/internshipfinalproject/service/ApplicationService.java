package com.lhind.internshipfinalproject.service;

import com.lhind.internshipfinalproject.dto.ApplicationDTO;
import com.lhind.internshipfinalproject.entity.Application;
import com.lhind.internshipfinalproject.entity.Job;
import com.lhind.internshipfinalproject.entity.User;
import com.lhind.internshipfinalproject.enums.ApplicationStatus;
import com.lhind.internshipfinalproject.mapper.ApplicationMapper;
import com.lhind.internshipfinalproject.repository.ApplicationRepository;
import com.lhind.internshipfinalproject.repository.JobRepository;
import com.lhind.internshipfinalproject.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final ApplicationMapper applicationMapper;
    private final JobRepository jobRepository;
    private final UserRepository userRepository;

    @Transactional
    public ApplicationDTO applyForJob(ApplicationDTO applicationDTO) {
        // Existing logic unchanged
        Job job = jobRepository.findById(applicationDTO.getJobId())
                .orElseThrow(() -> new RuntimeException("Job not found"));
        User jobSeeker = userRepository.findById(applicationDTO.getJobSeekerId())
                .orElseThrow(() -> new RuntimeException("Job seeker not found"));

        Application application = new Application();
        application.setJob(job);
        application.setJobSeeker(jobSeeker);
        application.setStatus(ApplicationStatus.PENDING);

        Application savedApplication = applicationRepository.save(application);
        return applicationMapper.toDTO(savedApplication);
    }

    // Existing method
    public Page<ApplicationDTO> getApplicationsByJobSeeker(Integer jobSeekerId, Pageable pageable) {
        return applicationRepository.findByJobSeekerId(jobSeekerId, pageable)
                .map(applicationMapper::toDTO);
    }

    // Existing method
    public Page<ApplicationDTO> getApplicationsByJobSeekerAndStatus(Integer jobSeekerId, ApplicationStatus status, Pageable pageable) {
        return applicationRepository.findByJobSeekerIdAndStatus(jobSeekerId, status, pageable)
                .map(applicationMapper::toDTO);
    }

    // NEW: filter by jobSeeker + jobTitle
    public Page<ApplicationDTO> getApplicationsByJobSeekerAndTitle(Integer jobSeekerId, String title, Pageable pageable) {
        return applicationRepository.findByJobSeekerIdAndJobTitle(jobSeekerId, title, pageable)
                .map(applicationMapper::toDTO);
    }

    // NEW: filter by jobSeeker + status + jobTitle
    public Page<ApplicationDTO> getApplicationsByJobSeekerStatusAndTitle(Integer jobSeekerId, ApplicationStatus status, String title, Pageable pageable) {
        return applicationRepository.findByJobSeekerIdAndStatusAndJobTitle(jobSeekerId, status, title, pageable)
                .map(applicationMapper::toDTO);
    }

    @Transactional
    public ApplicationDTO updateApplicationStatus(Integer applicationId, ApplicationStatus status, Integer employerId) {
        // Unchanged logic
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        if (!application.getJob().getEmployer().getId().equals(employerId)) {
            throw new RuntimeException("Unauthorized to update this application");
        }

        application.setStatus(status);
        return applicationMapper.toDTO(applicationRepository.save(application));
    }

    public Page<ApplicationDTO> getApplicationsByJobAndStatus(Integer jobId, ApplicationStatus status, Pageable pageable) {
        // existing method
        return applicationRepository.getApplicationsByJobAndStatus(jobId, status, pageable)
                .map(applicationMapper::toDTO);
    }
}
