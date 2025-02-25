package org.internship.main;

import org.internship.model.entity.User;
import org.internship.model.enums.RoleEnum;
import org.internship.repository.UserRepository;
import org.internship.repository.imp.UserRepositoryImpl;

import java.util.List;


public class Main {

    public static void main(String[] args) {
        UserRepository userRepository = new UserRepositoryImpl();

        // Create a new user
//        User user = new User();
//        user.setUsername("user1");
//        user.setPassword("password1");
//        user.setRole(RoleEnum.ADMIN);
//        userRepository.save(user);

        // Find all users
        List<User> users = userRepository.findAll();
        for (User u : users) {
            System.out.println(u.getUsername());
        }

    }
}