package com.lhind.internshipfinalproject;

import com.lhind.internshipfinalproject.mapper.UserMapper;
import com.lhind.internshipfinalproject.repository.UserRepository;
import com.lhind.internshipfinalproject.service.UserService;
import com.lhind.internshipfinalproject.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceTest {

    @Configuration
    static class TestConfig {

        @Bean
        @Primary
        public UserRepository userRepository() {
            // Create and return a Mockito mock for UserRepository
            return mock(UserRepository.class);
        }

        @Bean
        @Primary
        public UserMapper userMapper() {
            // Create and return a Mockito mock for UserMapper
            return mock(UserMapper.class);
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

        @Bean
        public UserService userService(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
            // Instantiate your UserServiceImpl with all required dependencies.
            return new UserServiceImpl(userRepository, userMapper, passwordEncoder);
        }
    }

    private final UserRepository userRepository;
    private final UserService userService;

    // Spring will inject the beans defined in TestConfig
    public UserServiceTest(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @Test
    void testGetAllUsers() {
        Pageable pageable = Pageable.ofSize(10);
        // Stub the repository method to return an empty page (avoid returning null)
        when(userRepository.findAll(pageable)).thenReturn(new PageImpl<>(Collections.emptyList()));

        Page<UserDTO> result = userService.getAllUsers(null, pageable);

        assertThat(result).isEmpty();
        verify(userRepository).findAll(pageable);
    }
}
