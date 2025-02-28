package org.internship.mapper;

import org.internship.model.dto.UserCreateDTO;
import org.internship.model.entity.User;

public class UserCreateMapper {

    public static User toEntity(UserCreateDTO dto) {
        if (dto == null) {
            return null;
        }
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword()); // You might later encrypt this in the service layer
        user.setRole(dto.getRole());
        return user;
    }
}
