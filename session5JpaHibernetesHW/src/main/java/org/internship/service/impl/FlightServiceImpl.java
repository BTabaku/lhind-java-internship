package org.internship.service.impl;

import org.internship.mapper.FlightMapper;
import org.internship.model.dto.FlightDTO;
import org.internship.model.entity.Flight;
import org.internship.repository.FlightRepository;
import org.internship.repository.imp.FlightRepositoryImpl;
import org.internship.service.FlightService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository = new FlightRepositoryImpl();
    private final FlightMapper flightMapper = new FlightMapper();

    @Override
    public FlightDTO createFlight(FlightDTO flightDTO) {
        // Convert DTO to entity
        Flight flight = flightMapper.toEntity(flightDTO);
        // Save the flight
        flightRepository.save(flight);
        // Convert saved entity back to DTO and return
        return flightMapper.toDTO(flight);
    }

    @Override
    public Optional<FlightDTO> getFlightById(Long id) {
        Flight flight = flightRepository.findById(id);
        if (flight != null) {
            return Optional.of(flightMapper.toDTO(flight));
        }
        return Optional.empty();
    }

    @Override
    public List<FlightDTO> getAllFlights() {
        List<Flight> flights = flightRepository.findAll();
        return flights.stream().map(flightMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public void updateFlight(Long id, FlightDTO flightDTO) {
        Flight existingFlight = flightRepository.findById(id);
        if (existingFlight != null) {
            // Update the fields from DTO
            existingFlight.setOrigin(flightDTO.getOrigin());
            existingFlight.setDestination(flightDTO.getDestination());
            existingFlight.setAirline(flightDTO.getAirline());
            existingFlight.setFlightNumber(flightDTO.getFlightNumber());
            existingFlight.setDepartureDate(flightDTO.getDepartureDate());
            existingFlight.setArrivalDate(flightDTO.getArrivalDate());
            // If status conversion is needed, update here.
            flightRepository.update(existingFlight);
        }
    }

    @Override
    public void deleteFlight(Long id) {
        Flight flight = flightRepository.findById(id);
        if (flight != null) {
            flightRepository.delete(flight);
        }
    }
}
