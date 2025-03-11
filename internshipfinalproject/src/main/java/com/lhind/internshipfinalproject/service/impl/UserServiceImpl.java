package com.lhind.internshipfinalproject.service.impl;

import com.lhind.internshipfinalproject.dto.UserDTO;
import com.lhind.internshipfinalproject.entity.User;
import com.lhind.internshipfinalproject.enums.Role;
import com.lhind.internshipfinalproject.mapper.UserMapper;
import com.lhind.internshipfinalproject.repository.UserRepository;
import com.lhind.internshipfinalproject.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper; // MapStruct mapper

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDTO registerUser(UserDTO userDTO) {
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
        existingUser.setPassword(userDTO.getPassword());
        existingUser.setRole(userDTO.getRole());
        userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }
}
