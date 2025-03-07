package com.lhind.internshipfinalproject.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "jobs")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    private String description;
    private String location;

    @ManyToOne
    @JoinColumn(name = "employer_id")
    private User employer;

}
