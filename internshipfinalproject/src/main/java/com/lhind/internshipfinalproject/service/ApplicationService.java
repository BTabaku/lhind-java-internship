package com.lhind.internshipfinalproject.service;

import com.lhind.internshipfinalproject.dto.ApplicationDTO;
import com.lhind.internshipfinalproject.enums.ApplicationStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ApplicationService {
    ApplicationDTO applyForJob(ApplicationDTO applicationDTO);
    ApplicationDTO updateApplicationStatus(Integer applicationId, ApplicationStatus status, Integer employerId);
    Page<ApplicationDTO> getApplicationsByJobSeeker(Integer jobSeekerId, Pageable pageable);
    Page<ApplicationDTO> getApplicationsByJobSeekerAndStatus(Integer jobSeekerId, ApplicationStatus status, Pageable pageable);
}
