package com.internship.session6springboot.controller;

import com.internship.session6springboot.dto.FlightDTO;
import com.internship.session6springboot.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @PostMapping
    public FlightDTO createFlight(@RequestBody FlightDTO flightDTO) {
        return flightService.createFlight(flightDTO);
    }

    @GetMapping("/{id}")
    public Optional<FlightDTO> getFlightById(@PathVariable Long id) {
        return flightService.getFlightById(id);
    }

    @GetMapping
    public List<FlightDTO> getAllFlights() {
        return flightService.getAllFlights();
    }

    @PutMapping("/{id}")
    public void updateFlight(@PathVariable Long id, @RequestBody FlightDTO flightDTO) {
        flightService.updateFlight(id, flightDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteFlight(@PathVariable Long id) {
        flightService.deleteFlight(id);
    }
}
