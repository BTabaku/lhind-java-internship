package com.internship.session6springboot.dto;

public class UserCreateDTO {
    private String username;
    private String password;
    private String role; // could be validated against RoleEnum values
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

    // Getters and Setters
    // ...
}
