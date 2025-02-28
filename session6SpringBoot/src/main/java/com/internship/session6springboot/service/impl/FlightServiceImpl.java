package com.internship.session6springboot.service.impl;

import com.internship.session6springboot.dto.FlightDTO;
import com.internship.session6springboot.entity.Flight;
import com.internship.session6springboot.enums.BookingStatus;
import com.internship.session6springboot.repository.FlightRepository;
import com.internship.session6springboot.service.FlightService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FlightServiceImpl implements FlightService {

    // You can inject this via Spring in a real app; here we assume manual instantiation.
    private final FlightRepository flightRepository;

    // Constructor injection (or use a no-arg constructor and manually assign)
    public FlightServiceImpl(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @Override
    public FlightDTO createFlight(FlightDTO flightDTO) {
        // Map DTO to entity
        Flight flight = new Flight();
        flight.setOrigin(flightDTO.getOrigin());
        flight.setDestination(flightDTO.getDestination());
        flight.setAirline(flightDTO.getAirline());
        flight.setFlightNumber(flightDTO.getFlightNumber());
        flight.setDepartureDate(flightDTO.getDepartureDate());
        flight.setArrivalDate(flightDTO.getArrivalDate());
        // Since flightDTO.getStatus() now returns a BookingStatus, assign it directly.
        flight.setStatus(flightDTO.getStatus());

        // Save the flight
        flightRepository.save(flight);

        // Map back to DTO (if FlightDTO expects BookingStatus, then assign directly)
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
        Optional<Flight> flightOpt = flightRepository.findById(id);
        if (flightOpt.isPresent()) {
            Flight flight = flightOpt.get();
            FlightDTO dto = new FlightDTO();
            dto.setId(flight.getId());
            dto.setOrigin(flight.getOrigin());
            dto.setDestination(flight.getDestination());
            dto.setAirline(flight.getAirline());
            dto.setFlightNumber(flight.getFlightNumber());
            dto.setDepartureDate(flight.getDepartureDate());
            dto.setArrivalDate(flight.getArrivalDate());
            dto.setStatus(BookingStatus.valueOf(flight.getStatus().name()));

            return Optional.of(dto);
        }
        return Optional.empty();
    }


    @Override
    public List<FlightDTO> getAllFlights() {
        List<Flight> flights = flightRepository.findAll();
        return flights.stream().map(flight -> {
            FlightDTO dto = new FlightDTO();
            dto.setId(flight.getId());
            dto.setOrigin(flight.getOrigin());
            dto.setDestination(flight.getDestination());
            dto.setAirline(flight.getAirline());
            dto.setFlightNumber(flight.getFlightNumber());
            dto.setDepartureDate(flight.getDepartureDate());
            dto.setArrivalDate(flight.getArrivalDate());
            dto.setStatus(BookingStatus.valueOf(flight.getStatus().name()));
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public void updateFlight(Long id, FlightDTO flightDTO) {
        Optional<Flight> flightOpt = flightRepository.findById(id);
        if (flightOpt.isPresent()) {
            Flight flight = flightOpt.get();
            flight.setOrigin(flightDTO.getOrigin());
            flight.setDestination(flightDTO.getDestination());
            flight.setAirline(flightDTO.getAirline());
            flight.setFlightNumber(flightDTO.getFlightNumber());
            flight.setDepartureDate(flightDTO.getDepartureDate());
            flight.setArrivalDate(flightDTO.getArrivalDate());
            flight.setStatus(BookingStatus.valueOf(String.valueOf(flightDTO.getStatus())));
            flightRepository.save(flight);
        } else {
            throw new RuntimeException("Flight not found");
        }
    }


    @Override
    public void deleteFlight(Long id) {
        Optional<Flight> flightOpt = flightRepository.findById(id);
        if (flightOpt.isPresent()) {
            flightRepository.delete(flightOpt.get());
        } else {
            throw new RuntimeException("Flight not found");
        }
    }

}
