package com.internship.session6springboot.service.impl;

import com.internship.session6springboot.dto.FlightDTO;
import com.internship.session6springboot.entity.Flight;
import com.internship.session6springboot.enums.BookingStatus;
import com.internship.session6springboot.repository.FlightRepository;
import com.internship.session6springboot.service.FlightService;
import com.internship.session6springboot.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;

    public FlightServiceImpl(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @Override
    public FlightDTO createFlight(FlightDTO flightDTO) {
        Flight flight = new Flight();
        flight.setOrigin(flightDTO.getOrigin());
        flight.setDestination(flightDTO.getDestination());
        flight.setAirline(flightDTO.getAirline());
        flight.setFlightNumber(flightDTO.getFlightNumber());
        flight.setDepartureDate(flightDTO.getDepartureDate());
        flight.setArrivalDate(flightDTO.getArrivalDate());
        flight.setStatus(flightDTO.getStatus());

        Flight savedFlight = flightRepository.save(flight);
        return convertToDto(savedFlight);
    }

    private FlightDTO convertToDto(Flight flight) {
        FlightDTO dto = new FlightDTO();
        dto.setId(flight.getId());
        dto.setOrigin(flight.getOrigin());
        dto.setDestination(flight.getDestination());
        dto.setAirline(flight.getAirline());
        dto.setFlightNumber(flight.getFlightNumber());
        dto.setDepartureDate(flight.getDepartureDate());
        dto.setArrivalDate(flight.getArrivalDate());
        dto.setStatus(flight.getStatus());
        return dto;
    }

    @Override
    public Optional<FlightDTO> getFlightById(Long id) {
        return flightRepository.findById(id)
                .map(this::convertToDto);
    }

    @Override
    public List<FlightDTO> getAllFlights() {
        return flightRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void updateFlight(Long id, FlightDTO flightDTO) {
        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Flight not found with id: " + id));

        flight.setOrigin(flightDTO.getOrigin());
        flight.setDestination(flightDTO.getDestination());
        flight.setAirline(flightDTO.getAirline());
        flight.setFlightNumber(flightDTO.getFlightNumber());
        flight.setDepartureDate(flightDTO.getDepartureDate());
        flight.setArrivalDate(flightDTO.getArrivalDate());
        flight.setStatus(flightDTO.getStatus());

        flightRepository.save(flight);
    }

    @Override
    public void deleteFlight(Long id) {
        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Flight not found with id: " + id));
        flightRepository.delete(flight);
    }
}