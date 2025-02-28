package com.internship.session6springboot.service;

import java.util.List;
import java.util.Optional;

import com.internship.session6springboot.dto.UserCreateDTO;
import com.internship.session6springboot.dto.UserResponseDTO;

public interface UserService {
    UserResponseDTO registerUser(UserCreateDTO userCreateDTO);
    Optional<UserResponseDTO> getUserById(Long id);
    List<UserResponseDTO> getAllUsers();
    void updateUser(Long id, UserCreateDTO userCreateDTO);
    void deleteUser(Long id);
}