package com.lhind.internshipfinalproject.mapper;

import com.lhind.internshipfinalproject.dto.JobDTO;
import com.lhind.internshipfinalproject.entity.Job;
import com.lhind.internshipfinalproject.entity.User;
import com.lhind.internshipfinalproject.repository.UserRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class JobMapper {

    @Autowired
    private UserRepository userRepository;

    // Map JobDTO to Job entity
    @Mapping(target = "employer", source = "employerId")
    @Mapping(target = "title", source = "title")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "location", source = "location")
    public abstract Job toEntity(JobDTO jobDTO);

    // Map Job entity to JobDTO
    @Mapping(target = "employerId", source = "employer.id")
    public abstract JobDTO toDTO(Job job);

    // Fetch User from employerId
    protected User mapEmployerIdToUser(Integer employerId) {
        return userRepository.findById(employerId)
                .orElseThrow(() -> new RuntimeException("Employer not found"));
    }
}