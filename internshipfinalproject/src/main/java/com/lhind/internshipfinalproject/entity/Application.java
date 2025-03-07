package com.lhind.internshipfinalproject.entity;


import com.lhind.internshipfinalproject.enums.ApplicationStatus;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "applications")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;

    @ManyToOne
    @JoinColumn(name = "job_seeker_id")
    private User jobSeeker;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;

}
