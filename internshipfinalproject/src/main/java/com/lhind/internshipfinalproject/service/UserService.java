package com.lhind.internshipfinalproject.service;

import com.lhind.internshipfinalproject.dto.UserDTO;
import com.lhind.internshipfinalproject.enums.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;

public interface UserService {
    UserDTO registerUser(UserDTO userDTO);
    Optional<UserDTO> getUserById(Integer id);
    Page<UserDTO> getAllUsers(Role role, Pageable pageable);
    void updateUser(Integer id, UserDTO userDTO);
    void deleteUser(Integer id);
    // Optionally add:
    UserDTO saveUser(UserDTO userDTO);
}
