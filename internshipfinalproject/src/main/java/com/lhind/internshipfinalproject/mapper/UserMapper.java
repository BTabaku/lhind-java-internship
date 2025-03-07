package com.lhind.internshipfinalproject.mapper;

import com.lhind.internshipfinalproject.dto.UserDTO;
import com.lhind.internshipfinalproject.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class UserMapper {
    public abstract UserDTO toDTO(User user);

    @Mapping(target = "id", ignore = true)
    public abstract User toEntity(UserDTO userDTO);
}
