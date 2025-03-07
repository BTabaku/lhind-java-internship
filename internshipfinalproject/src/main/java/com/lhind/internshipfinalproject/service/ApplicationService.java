package com.lhind.internshipfinalproject.service;

import com.lhind.internshipfinalproject.dto.ApplicationDTO;
import com.lhind.internshipfinalproject.entity.Application;
import com.lhind.internshipfinalproject.enums.ApplicationStatus;
import com.lhind.internshipfinalproject.mapper.ApplicationMapper;
import com.lhind.internshipfinalproject.repository.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplicationService {
    private final ApplicationRepository applicationRepository;
    private final ApplicationMapper applicationMapper;

    public ApplicationDTO applyForJob(ApplicationDTO applicationDTO) {
        Application application = applicationMapper.toEntity(applicationDTO);
        application.setStatus(ApplicationStatus.PENDING);
        Application savedApplication = applicationRepository.save(application);
        return applicationMapper.toDTO(savedApplication);
    }
    public Page<ApplicationDTO> getApplicationsByJobAndStatus(Integer jobId, ApplicationStatus status, Pageable pageable) {
        if (status != null) {
            return applicationRepository.findByJobIdAndStatus(jobId, status, pageable)
                    .map(applicationMapper::toDTO);
        }
        return applicationRepository.findByJobId(jobId, pageable)
                .map(applicationMapper::toDTO);
    }

    public ApplicationDTO updateApplicationStatus(Integer applicationId, ApplicationStatus status) {
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found"));
        application.setStatus(status);
        return applicationMapper.toDTO(applicationRepository.save(application));
    }

}
