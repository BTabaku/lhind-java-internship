package org.internship.service.impl;

import org.internship.mapper.UserCreateMapper;
import org.internship.mapper.UserMapper;
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
    // Use UserCreateMapper directly (static) if desired

    @Override
    public UserResponseDTO registerUser(UserCreateDTO userCreateDTO) {
        try {
            // Convert registration DTO to entity
            User user = UserCreateMapper.toEntity(userCreateDTO);
            // Save the user
            userRepository.save(user);
            // Return a response DTO (which aggregates user and user details)
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
    public void updateUser(UserResponseDTO userResponseDTO) {
        // Convert response DTO back to entity if needed; be cautious as response DTO might be read-only.
        // Alternatively, create a separate update DTO.
        User user = new User();
        user.setId(userResponseDTO.getId());
        user.setUsername(userResponseDTO.getUsername());
        user.setRole(userResponseDTO.getRole());
        // Note: UserDetails update might be handled separately.
        userRepository.update(user);
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id);
        if (user != null) {
            userRepository.delete(user);
        }
    }
}
