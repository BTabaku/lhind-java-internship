package com.lhind.internshipfinalproject.mapper;

import com.lhind.internshipfinalproject.dto.ApplicationDTO;
import com.lhind.internshipfinalproject.entity.Application;
import com.lhind.internshipfinalproject.dto.UserDTO;
import com.lhind.internshipfinalproject.entity.User;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class ApplicationMapper {
    public abstract ApplicationDTO toDTO(Application application);
    public abstract Application toEntity(ApplicationDTO applicationDTO);
}
