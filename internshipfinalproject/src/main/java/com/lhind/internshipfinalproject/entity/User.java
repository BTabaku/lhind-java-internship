package com.lhind.internshipfinalproject.entity;

import com.lhind.internshipfinalproject.enums.Role;
                      import jakarta.persistence.*;
                      import lombok.Data;

                      import java.util.ArrayList;
                      import java.util.List;

@Entity
@Data
@Table(name = "users")
public class User {

    @OneToMany(mappedBy = "employer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Job> jobs = new ArrayList<>();

    @OneToMany(mappedBy = "jobSeeker", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Application> applications = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false, name = "username")
    private String username;

    @Column(nullable = false, name = "password")
    private String password;

    @Column(nullable = false, name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

}
