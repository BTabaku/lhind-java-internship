package com.lhind.internshipfinalproject.mapper;

import com.lhind.internshipfinalproject.dto.UserDTO;
import com.lhind.internshipfinalproject.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


// UserMapper.java
@Mapper(componentModel = "spring")
public abstract class UserMapper {
    @Mapping(target = "id", ignore = true)
    public abstract User toEntity(UserDTO userDTO);

    @Mapping(target = "username", source = "username")
    @Mapping(target = "password", source = "password")
    @Mapping(target = "role", source = "role")
    public abstract UserDTO toDTO(User user);
}