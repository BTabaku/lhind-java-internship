package com.internship.session6springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class Session6SpringBootApplication {

    private static final Logger log = LoggerFactory.getLogger(Session6SpringBootApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(Session6SpringBootApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            log.info("🟢 Application started successfully!");
            log.info("🔗 Endpoints:");
            log.info("   - Users: http://localhost:8085/api/v1/users");
            log.info("   - Flights: http://localhost:8085/api/v1/flights");
            log.info("   - Bookings: http://localhost:8085/api/v1/bookings");
        };
    }

}
