package org.internship.model.entity;

import jakarta.persistence.*;
import org.internship.model.enums.RoleEnum;

@Entity
@Table(name = "user")
public class User {

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private UserDetails userDetails;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private RoleEnum role;

    public User() {
    }

    public User(String username, String password, RoleEnum role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // Getters and Setters
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
    public UserDetails getUserDetails() {
        return userDetails;
    }
    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
        if (userDetails != null && userDetails.getUser() != this) {
            userDetails.setUser(this);
        }
    }
}
