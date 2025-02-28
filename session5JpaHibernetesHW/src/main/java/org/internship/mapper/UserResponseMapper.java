package org.internship.mapper;

import org.internship.model.dto.UserResponseDTO;
import org.internship.model.entity.User;

public class UserResponseMapper extends AbstractMapper<User, UserResponseDTO> {

    @Override
    public UserResponseDTO toDTO(User user) {
        if (user == null) {
            return null;
        }
        String firstName = null, lastName = null, email = null, phoneNumber = null;
        if (user.getUserDetails() != null) {
            firstName = user.getUserDetails().getFirstName();
            lastName = user.getUserDetails().getLastName();
            email = user.getUserDetails().getEmail();
            phoneNumber = user.getUserDetails().getPhoneNumber();
        }
        return new UserResponseDTO(
                user.getId(),
                user.getUsername(),
                user.getRole(),
                firstName,
                lastName,
                email,
                phoneNumber
        );
    }

    @Override
    public User toEntity(UserResponseDTO dto) {
        throw new UnsupportedOperationException("Mapping from DTO to entity is not supported.");
    }
}
