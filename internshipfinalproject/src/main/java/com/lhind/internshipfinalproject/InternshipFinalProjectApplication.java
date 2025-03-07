package com.lhind.internshipfinalproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InternshipFinalProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(InternshipFinalProjectApplication.class, args);
    }
}

//TODO List:

//Implement Security: Add SecurityConfig.java in the config folder.
//
//Add DTOs: Create JobDTO, ApplicationDTO, etc., in the dto folder.
//
//Add Validation: Use @Valid in controllers.
//
//Complete Other Controllers: Admin, Employer, and Review APIs.