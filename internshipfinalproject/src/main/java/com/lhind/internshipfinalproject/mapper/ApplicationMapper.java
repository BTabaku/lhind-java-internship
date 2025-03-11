package com.lhind.internshipfinalproject.mapper;

import com.lhind.internshipfinalproject.dto.ApplicationDTO;
import com.lhind.internshipfinalproject.entity.Application;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class ApplicationMapper {

    // Map job and jobSeeker IDs from the entity to the DTO.
    @Mapping(target = "jobId", source = "job.id")
    @Mapping(target = "jobSeekerId", source = "jobSeeker.id")
    public abstract ApplicationDTO toDTO(Application application);

    // Convert DTO to entity; ignore relational fields since they are set manually.
    @Mapping(target = "job", ignore = true)
    @Mapping(target = "jobSeeker", ignore = true)
    public abstract Application toEntity(ApplicationDTO applicationDTO);
}
