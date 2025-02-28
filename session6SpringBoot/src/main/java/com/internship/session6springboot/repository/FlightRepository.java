package com.internship.session6springboot.repository;

import com.internship.session6springboot.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

import org.internship.util.Queries;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {

    @Query(Queries.GET_ALL_FLIGHTS)
    List<Flight> findAllFlights();

    @Query(Queries.GET_FLIGHT_BY_ID)
    Flight findFlightById(@Param("id") Long id);

    @Query(Queries.GET_FLIGHTS_BY_DEPARTURE_AND_ORIGIN)
    List<Flight> findByDepartureDateAndOrigin(@Param("start") LocalDateTime start,
                                              @Param("end") LocalDateTime end,
                                              @Param("origin") String origin);
}
