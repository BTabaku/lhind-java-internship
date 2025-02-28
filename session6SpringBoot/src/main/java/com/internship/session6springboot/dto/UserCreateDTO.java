package com.internship.session6springboot.dto;

import com.internship.session6springboot.entity.User;
import com.internship.session6springboot.enums.RoleEnum;


public class UserCreateDTO {
    private Long id; // Optional: used for updates
    private String username;
    private String password;
    private RoleEnum role; // Expecting values like "ADMIN" or "USER"
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

    // Constructors
    public UserCreateDTO() {}

    public UserCreateDTO(Long id, String username, String password, RoleEnum role, String firstName, String lastName, String email, String phoneNumber) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public RoleEnum getRole() {
        return role;
    }
    public void setRole(RoleEnum role) {
        this.role = role;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
