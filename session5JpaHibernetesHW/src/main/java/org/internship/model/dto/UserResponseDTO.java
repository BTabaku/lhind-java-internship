package org.internship.model.dto;

import org.internship.model.enums.RoleEnum;

public class UserResponseDTO {
    private Long id;
    private String username;
    private RoleEnum role;

    // Additional fields from UserDetails:
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

    public UserResponseDTO() {
    }

    public UserResponseDTO(Long id, String username, RoleEnum role,
                           String firstName, String lastName, String email, String phoneNumber) {
        this.id = id;
        this.username = username;
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    // Getters and Setters for all fields (omitted here for brevity)

    @Override
    public String toString() {
        return "UserResponseDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", role=" + role +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
