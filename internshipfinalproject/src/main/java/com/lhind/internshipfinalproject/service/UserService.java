package com.lhind.internshipfinalproject.service;

import com.lhind.internshipfinalproject.dto.UserDTO;
import com.lhind.internshipfinalproject.entity.User;
import com.lhind.internshipfinalproject.enums.Role;
import com.lhind.internshipfinalproject.mapper.UserMapper;
import com.lhind.internshipfinalproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public Page<UserDTO> getAllUsers(Role role, Pageable pageable) {
        log.info("Fetching all users with role: {}", role);
        if (role != null) {
            return userRepository.findByRole(role, pageable).map(userMapper::toDTO);
        }
        return userRepository.findAll(pageable).map(userMapper::toDTO);
    }

    @Transactional
    public void saveUser(UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userRepository.save(user);
        log.info("User saved: {}", user.getUsername());
    }

    public void deleteUserById(Integer id) {
        log.info("Deleting user with ID: {}", id);
        userRepository.deleteById(id);
    }
}