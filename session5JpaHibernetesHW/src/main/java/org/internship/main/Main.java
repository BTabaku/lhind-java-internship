package org.internship.main;

import org.internship.configuration.EntityManagerConfiguration;
import org.internship.model.dto.FlightDTO;
import org.internship.model.dto.UserCreateDTO;
import org.internship.model.dto.UserResponseDTO;
import org.internship.model.enums.BookingStatus; // or FlightStatusEnum if used
import org.internship.model.enums.RoleEnum;
import org.internship.service.FlightService;
import org.internship.service.UserService;
import org.internship.service.impl.FlightServiceImpl;
import org.internship.service.impl.UserServiceImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class Main {
     public static void main(String[] args) {

          // -------------------------------
          // Flight Operations
          // -------------------------------
          FlightService flightService = new FlightServiceImpl();

          // Create a new Flight
          FlightDTO flightDTO = new FlightDTO(
                  null, // id will be auto-generated
                  "New York",
                  "London",
                  "British Airways",
                  "BA117",
                  LocalDateTime.of(2025, 3, 1, 15, 30),
                  LocalDateTime.of(2025, 3, 1, 23, 45),
                  BookingStatus.PENDING.name() // converting enum to String
          );

          FlightDTO createdFlight = flightService.createFlight(flightDTO);
          System.out.println("Flight created: " + createdFlight);

          // Retrieve Flight by ID
          Optional<FlightDTO> retrievedFlightOpt = flightService.getFlightById(createdFlight.getId());
          if (retrievedFlightOpt.isPresent()) {
               System.out.println("Retrieved Flight: " + retrievedFlightOpt.get());
          } else {
               System.out.println("Flight not found");
          }

          // Update Flight: change destination to "Paris"
          flightDTO.setDestination("Paris");
          flightService.updateFlight(createdFlight.getId(), flightDTO);
          System.out.println("Flight updated.");

          // List all Flights
          List<FlightDTO> allFlights = flightService.getAllFlights();
          System.out.println("All Flights:");
          allFlights.forEach(System.out::println);

          // Delete the Flight
          flightService.deleteFlight(createdFlight.getId());
          System.out.println("Flight deleted.");

          // -------------------------------
          // User Operations
          // -------------------------------
          UserService userService = new UserServiceImpl();

          // Register a new user
          UserCreateDTO userCreateDTO = new UserCreateDTO("btabaku", "password123", RoleEnum.USER);
          UserResponseDTO registeredUser = userService.registerUser(userCreateDTO);
          System.out.println("Registered User: " + registeredUser);

          // Retrieve user by ID
          Optional<UserResponseDTO> retrievedUserOpt = userService.getUserById(registeredUser.getId());
          if (retrievedUserOpt.isPresent()) {
               System.out.println("Retrieved User: " + retrievedUserOpt.get());
          } else {
               System.out.println("User not found");
          }

          // List all users
          List<UserResponseDTO> allUsers = userService.getAllUsers();
          System.out.println("All Users:");
          allUsers.forEach(System.out::println);

          // Update user: change username to "john_updated"
          userService.updateUser(registeredUser.getId(), new UserCreateDTO("john_updated", "password123", RoleEnum.USER));
          System.out.println("User updated.");

          // Retrieve user after update
          Optional<UserResponseDTO> updatedUserOpt = userService.getUserById(registeredUser.getId());
          updatedUserOpt.ifPresent(user -> System.out.println("Updated User: " + user));

          // Delete the user
          userService.deleteUser(registeredUser.getId());
          System.out.println("User deleted.");

          // Shutdown the EntityManagerFactory (call once at the end)
          EntityManagerConfiguration.shutdown();
     }
}
