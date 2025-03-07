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

    @Mapping(target = "employer", source = "employerId")
    public abstract Job toEntity(JobDTO jobDTO);

    public abstract JobDTO toDTO(Job job);

    protected User mapEmployerIdToUser(Integer employerId) {
        return userRepository.findById(employerId)
                .orElseThrow(() -> new RuntimeException("Employer not found"));
    }
}