package com.internship.session6springboot.repository;

import org.internship.model.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    // Find flights departing on a specific date from a given airport (origin)
    List<Flight> findByDepartureDateBetweenAndOrigin(LocalDateTime start, LocalDateTime end, String origin);
}
