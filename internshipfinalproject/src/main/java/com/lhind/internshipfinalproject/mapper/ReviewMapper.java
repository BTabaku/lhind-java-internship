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

    // Entity → DTO
    @Mapping(target = "jobId", source = "job.id")
    @Mapping(target = "employerId", source = "employer.id")
    @Mapping(target = "content", source = "content")  // renamed
    public abstract ReviewDto toDTO(Review review);

    // DTO → Entity
    @Mapping(target = "job", source = "jobId", qualifiedByName = "mapJobIdToJob")
    @Mapping(target = "employer", source = "employerId", qualifiedByName = "mapEmployerIdToUser")
    @Mapping(target = "content", source = "content")  // renamed
    public abstract Review toEntity(ReviewDto reviewDto);

    // Helpers
    @Named("mapJobIdToJob")
    protected Job mapJobIdToJob(Integer jobId) {
        return jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found with id: " + jobId));
    }

    @Named("mapEmployerIdToUser")
    protected User mapEmployerIdToUser(Integer employerId) {
        return userRepository.findById(employerId)
                .orElseThrow(() -> new RuntimeException("Employer not found with id: " + employerId));
    }
}
