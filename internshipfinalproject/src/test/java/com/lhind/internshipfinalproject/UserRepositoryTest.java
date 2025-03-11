package com.lhind.internshipfinalproject;

import com.lhind.internshipfinalproject.entity.User;
import com.lhind.internshipfinalproject.enums.Role;
import com.lhind.internshipfinalproject.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSaveUser() {
        User user = new User();
        user.setUsername("jobseeker");
        user.setPassword("password");
        user.setRole(Role.JOB_SEEKER);

        userRepository.save(user);

        User savedUser = userRepository.findByUsername("jobseeker").orElse(null);
        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getRole()).isEqualTo(Role.JOB_SEEKER);
    }
}