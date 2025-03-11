package com.lhind.internshipfinalproject.service.impl;

import com.lhind.internshipfinalproject.dto.UserDTO;
import com.lhind.internshipfinalproject.entity.User;
import com.lhind.internshipfinalproject.enums.Role;
import com.lhind.internshipfinalproject.mapper.UserMapper;
import com.lhind.internshipfinalproject.repository.UserRepository;
import com.lhind.internshipfinalproject.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    // Constructor injection (order: userRepository, passwordEncoder, userMapper)
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    @Override
    public UserDTO registerUser(UserDTO userDTO) {
        // Optionally, you can encode the password before saving:
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        User user = userMapper.toEntity(userDTO);
        user = userRepository.save(user);
        return userMapper.toDTO(user);
    }

    @Override
    public Optional<UserDTO> getUserById(Integer id) {
        return userRepository.findById(id).map(userMapper::toDTO);
    }

    @Override
    public Page<UserDTO> getAllUsers(Role role, Pageable pageable) {
        if (role != null) {
            return userRepository.findByRole(role, pageable)
                    .map(userMapper::toDTO);
        } else {
            return userRepository.findAll(pageable)
                    .map(userMapper::toDTO);
        }
    }

    @Override
    public void updateUser(Integer id, UserDTO userDTO) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        existingUser.setUsername(userDTO.getUsername());
        // Optionally, encode the password if it is being updated:
        existingUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        existingUser.setRole(userDTO.getRole());
        userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        return registerUser(userDTO);
    }
}
