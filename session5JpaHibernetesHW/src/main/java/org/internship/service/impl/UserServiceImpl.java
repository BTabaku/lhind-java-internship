package org.internship.service.impl;

import org.internship.mapper.UserCreateMapper;
import org.internship.mapper.UserResponseMapper;
import org.internship.model.dto.UserCreateDTO;
import org.internship.model.dto.UserResponseDTO;
import org.internship.model.entity.User;
import org.internship.repository.UserRepository;
import org.internship.repository.imp.UserRepositoryImpl;
import org.internship.service.UserService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository = new UserRepositoryImpl();
    private final UserResponseMapper userResponseMapper = new UserResponseMapper();

    @Override
    public UserResponseDTO registerUser(UserCreateDTO userCreateDTO) {
        try {
            // Convert registration DTO to entity
            User user = UserCreateMapper.toEntity(userCreateDTO);
            // Save the user (password is stored as provided)
            userRepository.save(user);
            // Return a response DTO (aggregates user and user details)
            return userResponseMapper.toDTO(user);
        } catch (Exception ex) {
            System.err.println("Error registering user: " + ex.getMessage());
            throw ex;
        }
    }

    @Override
    public Optional<UserResponseDTO> getUserById(Long id) {
        User user = userRepository.findById(id);
        if (user != null) {
            return Optional.of(userResponseMapper.toDTO(user));
        }
        return Optional.empty();
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(userResponseMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public void updateUser(Long id, UserCreateDTO userCreateDTO) {
        User existingUser = userRepository.findById(id);
        if (existingUser != null) {
            // Update basic fields from UserCreateDTO
            existingUser.setUsername(userCreateDTO.getUsername());
            existingUser.setPassword(userCreateDTO.getPassword());
            existingUser.setRole(userCreateDTO.getRole());
            userRepository.update(existingUser);
        }
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id);
        if (user != null) {
            userRepository.delete(user);
        }
    }
}
