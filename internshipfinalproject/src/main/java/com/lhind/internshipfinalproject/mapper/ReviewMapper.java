package com.lhind.internshipfinalproject.mapper;

import com.lhind.internshipfinalproject.dto.ReviewDto;
import com.lhind.internshipfinalproject.entity.Job;
import com.lhind.internshipfinalproject.entity.Review;
import com.lhind.internshipfinalproject.entity.User;
import com.lhind.internshipfinalproject.repository.JobRepository;
import com.lhind.internshipfinalproject.repository.UserRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class ReviewMapper {

    @Autowired
    protected JobRepository jobRepository;

    @Autowired
    protected UserRepository userRepository;

    // Map Review entity to ReviewDto
    @Mapping(target = "jobId", source = "job.id")
    @Mapping(target = "employeeId", source = "employee.id")
    public abstract ReviewDto toDTO(Review review);

    // Map ReviewDto to Review entity
    @Mapping(target = "job", source = "jobId")
    @Mapping(target = "employee", source = "employeeId")
    public abstract Review toEntity(ReviewDto reviewDto);

    // Fetch Job from jobId
    protected Job mapJobIdToJob(Integer jobId) {
        return jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));
    }

    // Fetch User from employeeId
    protected User mapEmployeeIdToUser(Integer employeeId) {
        return userRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }
}