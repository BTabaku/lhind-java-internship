package com.internship.session6springboot.service;

import com.internship.session6springboot.dto.FlightDTO;
import java.util.List;
import java.util.Optional;

public interface FlightService {
    FlightDTO createFlight(FlightDTO flightDTO);
    Optional<FlightDTO> getFlightById(Long id);
    List<FlightDTO> getAllFlights();
    void updateFlight(Long id, FlightDTO flightDTO);
    void deleteFlight(Long id);
}