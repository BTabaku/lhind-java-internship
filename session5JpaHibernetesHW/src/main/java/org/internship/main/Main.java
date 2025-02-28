package org.internship.main;

import org.internship.configuration.EntityManagerConfiguration;
import org.internship.model.dto.UserCreateDTO;
import org.internship.model.dto.UserResponseDTO;
import org.internship.model.enums.RoleEnum;
import org.internship.service.UserService;
import org.internship.service.impl.UserServiceImpl;

import java.util.Optional;
import java.util.List;

public class Main {

     public static void main(String[] args) {

          UserService userService = new UserServiceImpl();

          // Register a new user
          UserCreateDTO createDTO = new UserCreateDTO("bafti40", "password123", RoleEnum.USER);
          UserResponseDTO registeredUser = userService.registerUser(createDTO);
          System.out.println("Registered user: " + registeredUser);

          // Retrieve the user by ID
          Optional<UserResponseDTO> userOpt = userService.getUserById(registeredUser.getId());
          if(userOpt.isPresent()){
               System.out.println("Fetched user: " + userOpt.get());
          } else {
               System.out.println("User not found.");
          }

          // Retrieve all users
          List<UserResponseDTO> users = userService.getAllUsers();
          System.out.println("All users:");
          users.forEach(System.out::println);

          // Optionally, update or delete the user...
          // For example, updating the user's username:
          // userService.updateUser(registeredUser.getId(), new UserCreateDTO("john_updated", "password123", RoleEnum.USER));

          // Delete the user (if desired)
          // userService.deleteUser(registeredUser.getId());
          // System.out.println("User deleted.");

          // Finally, shutdown the EntityManagerFactory once all operations are complete.
          EntityManagerConfiguration.shutdown();
     }
}
