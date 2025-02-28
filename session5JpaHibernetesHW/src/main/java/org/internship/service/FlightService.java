package org.internship.service;

import org.internship.model.dto.FlightDTO;
import java.util.List;
import java.util.Optional;

public interface FlightService {
    FlightDTO createFlight(FlightDTO flightDTO);
    Optional<FlightDTO> getFlightById(Long id);
    List<FlightDTO> getAllFlights();
    void updateFlight(Long id, FlightDTO flightDTO);
    void deleteFlight(Long id);
}
