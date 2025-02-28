package org.internship.service;

import org.internship.model.dto.UserCreateDTO;
import org.internship.model.dto.UserResponseDTO;
import java.util.List;
import java.util.Optional;

public interface UserService {
    UserResponseDTO registerUser(UserCreateDTO userCreateDTO);
    Optional<UserResponseDTO> getUserById(Long id);
    List<UserResponseDTO> getAllUsers();
    void updateUser(Long id, UserCreateDTO userCreateDTO);
    void deleteUser(Long id);
}
