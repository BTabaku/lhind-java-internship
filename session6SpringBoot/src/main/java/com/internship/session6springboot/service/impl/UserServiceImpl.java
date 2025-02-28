package com.internship.session6springboot.service.impl;

import com.internship.session6springboot.dto.UserCreateDTO;
import com.internship.session6springboot.dto.UserResponseDTO;
import com.internship.session6springboot.entity.User;
import com.internship.session6springboot.enums.RoleEnum;
import com.internship.session6springboot.repository.UserRepository;
import com.internship.session6springboot.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    // Constructor injection (or instantiate manually)
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponseDTO registerUser(UserCreateDTO userCreateDTO) {
        // Map DTO to entity manually
        User user = new User();
        user.setUsername(userCreateDTO.getUsername());
        user.setPassword(userCreateDTO.getPassword());
        user.setRole(userCreateDTO.getRole()); // RoleEnum provided directly

        userRepository.save(user);

        // Map entity to response DTO
        UserResponseDTO responseDTO = new UserResponseDTO();
        responseDTO.setId(user.getId());
        responseDTO.setUsername(user.getUsername());
        responseDTO.setRole(user.getRole().name());
        // Optionally set user details if available
        if (user.getUserDetails() != null) {
            responseDTO.setFirstName(user.getUserDetails().getFirstName());
            responseDTO.setLastName(user.getUserDetails().getLastName());
            responseDTO.setEmail(user.getUserDetails().getEmail());
            responseDTO.setPhoneNumber(user.getUserDetails().getPhoneNumber());
        }
        return responseDTO;
    }

    @Override
    public Optional<UserResponseDTO> getUserById(Long id) {
        Optional<User> optUser = userRepository.findById(id);
        if (optUser.isPresent()) {
            User user = optUser.get();
            UserResponseDTO dto = new UserResponseDTO();
            dto.setId(user.getId());
            dto.setUsername(user.getUsername());
            dto.setRole(String.valueOf(user.getRole()));
            if (user.getUserDetails() != null) {
                dto.setFirstName(user.getUserDetails().getFirstName());
                dto.setLastName(user.getUserDetails().getLastName());
                dto.setEmail(user.getUserDetails().getEmail());
                dto.setPhoneNumber(user.getUserDetails().getPhoneNumber());
            }
            return Optional.of(dto);
        }
        return Optional.empty();
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> {
            UserResponseDTO dto = new UserResponseDTO();
            dto.setId(user.getId());
            dto.setUsername(user.getUsername());
            dto.setRole(String.valueOf(user.getRole()));
            if (user.getUserDetails() != null) {
                dto.setFirstName(user.getUserDetails().getFirstName());
                dto.setLastName(user.getUserDetails().getLastName());
                dto.setEmail(user.getUserDetails().getEmail());
                dto.setPhoneNumber(user.getUserDetails().getPhoneNumber());
            }
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public void updateUser(Long id, UserCreateDTO userCreateDTO) {
        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setUsername(userCreateDTO.getUsername());
            user.setPassword(userCreateDTO.getPassword());
            user.setRole(userCreateDTO.getRole());
            userRepository.save(user);
        } else {
            throw new RuntimeException("User not found");
        }
    }


    @Override
    public void deleteUser(Long id) {
        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isPresent()) {
            userRepository.delete(userOpt.get());
        } else {
            throw new RuntimeException("User not found");
        }
    }
}
