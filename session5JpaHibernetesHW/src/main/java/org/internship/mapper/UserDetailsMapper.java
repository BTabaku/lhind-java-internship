package org.internship.mapper;

import org.internship.model.dto.UserDetailsDTO;
import org.internship.model.entity.UserDetails;

public class UserDetailsMapper extends AbstractMapper<UserDetails, UserDetailsDTO> {

    @Override
    public UserDetailsDTO toDTO(UserDetails entity) {
        if (entity == null) {
            return null;
        }
        return new UserDetailsDTO(entity.getId(), entity.getFirstName(), entity.getLastName(),
                entity.getEmail(), entity.getPhoneNumber());
    }

    @Override
    public UserDetails toEntity(UserDetailsDTO dto) {
        if (dto == null) {
            return null;
        }
        UserDetails userDetails = new UserDetails();
        userDetails.setId(dto.getId());
        userDetails.setFirstName(dto.getFirstName());
        userDetails.setLastName(dto.getLastName());
        userDetails.setEmail(dto.getEmail());
        userDetails.setPhoneNumber(dto.getPhoneNumber());
        return userDetails;
    }
}
