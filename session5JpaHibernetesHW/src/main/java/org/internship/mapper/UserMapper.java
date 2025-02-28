package org.internship.mapper;

import org.internship.model.dto.UserDTO;
import org.internship.model.entity.User;
import org.internship.model.entity.UserDetails;

public class UserMapper extends AbstractMapper<User, UserDTO> {

    @Override
    public UserDTO toDTO(User user) {
        if (user == null) {
            return null;
        }
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setRole(user.getRole());
        // If you want to include details from UserDetails:
        if (user.getUserDetails() != null) {
            dto.setFirstName(user.getUserDetails().getFirstName());
            dto.setLastName(user.getUserDetails().getLastName());
            dto.setEmail(user.getUserDetails().getEmail());
            dto.setPhoneNumber(user.getUserDetails().getPhoneNumber());
        }
        return dto;
    }

    @Override
    public User toEntity(UserDTO dto) {
        if (dto == null) {
            return null;
        }
        User user = new User();
        user.setId(dto.getId());
        user.setUsername(dto.getUsername());
        user.setRole(dto.getRole());
        // If you need to map user details back, do it separately.
        return user;
    }
}
