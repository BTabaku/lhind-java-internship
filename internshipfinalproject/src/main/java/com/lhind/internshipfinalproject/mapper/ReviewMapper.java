package com.lhind.internshipfinalproject.mapper;

import com.lhind.internshipfinalproject.dto.ReviewDto;
import com.lhind.internshipfinalproject.entity.Job;
import com.lhind.internshipfinalproject.entity.Review;
import com.lhind.internshipfinalproject.entity.User;
import com.lhind.internshipfinalproject.repository.JobRepository;
import com.lhind.internshipfinalproject.repository.UserRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class ReviewMapper {

    @Autowired
    protected JobRepository jobRepository;

    @Autowired
    protected UserRepository userRepository;

    // Map Review entity to ReviewDto
    @Mapping(target = "jobId", source = "job.id")
    @Mapping(target = "employerId", source = "employer.id")
    public abstract ReviewDto toDTO(Review review);

    // Map ReviewDto to Review entity with custom fetch logic
    @Mapping(target = "job", source = "jobId", qualifiedByName = "mapJobIdToJob")
    @Mapping(target = "employer", source = "employerId", qualifiedByName = "mapEmployerIdToUser")
    public abstract Review toEntity(ReviewDto reviewDto);

    // Fetch Job from jobId
    @Named("mapJobIdToJob")
    protected Job mapJobIdToJob(Integer jobId) {
        return jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found with id: " + jobId));
    }

    // Fetch User from employerId
    @Named("mapEmployerIdToUser")
    protected User mapEmployerIdToUser(Integer employerId) {
        return userRepository.findById(employerId)
                .orElseThrow(() -> new RuntimeException("Employer not found with id: " + employerId));
    }
}
