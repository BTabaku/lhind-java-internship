package com.lhind.internshipfinalproject;

import com.lhind.internshipfinalproject.dto.UserDTO;
import com.lhind.internshipfinalproject.entity.User;
import com.lhind.internshipfinalproject.mapper.UserMapper;
import com.lhind.internshipfinalproject.repository.UserRepository;
import com.lhind.internshipfinalproject.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setup() {
        // MockitoExtension initializes the mocks
    }

    @Test
    void testGetAllUsers() {
        Pageable pageable = Pageable.ofSize(10);
        // Create an empty Page of Users (not UserDTO)
        Page<User> emptyUserPage = new PageImpl<>(Collections.emptyList(), pageable, 0);
        when(userRepository.findAll(pageable)).thenReturn(emptyUserPage);

        // When the service calls findAll(), it then maps the empty list via userMapper::toDTO,
        // resulting in an empty Page<UserDTO>
        Page<UserDTO> result = userService.getAllUsers(null, pageable);

        assertThat(result).isEmpty();
        verify(userRepository).findAll(pageable);
    }
}
