package com.internship.session6springboot;

import com.internship.session6springboot.dto.FlightDTO;
import com.internship.session6springboot.entity.Flight;
import com.internship.session6springboot.enums.BookingStatus;
import com.internship.session6springboot.exception.ResourceNotFoundException;
import com.internship.session6springboot.repository.FlightRepository;
import com.internship.session6springboot.service.impl.FlightServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FlightServiceImplTest {

    @Mock
    private FlightRepository flightRepository;

    @InjectMocks
    private FlightServiceImpl flightService;

    private final LocalDateTime now = LocalDateTime.now();

    @Test
    void createFlight_ShouldReturnFlightDTO() {
        // Arrange
        FlightDTO inputDTO = new FlightDTO();
        inputDTO.setOrigin("JFK");
        inputDTO.setDestination("LAX");
        inputDTO.setAirline("Delta"); // Added required field
        inputDTO.setFlightNumber("DL123"); // Added required field
        inputDTO.setDepartureDate(now); // Added required field
        inputDTO.setArrivalDate(now.plusHours(5)); // Added required field
        inputDTO.setStatus(BookingStatus.CONFIRMED);

        Flight savedFlight = new Flight();
        savedFlight.setId(1L);
        savedFlight.setOrigin("JFK");
        savedFlight.setDestination("LAX");
        savedFlight.setAirline("Delta");
        savedFlight.setFlightNumber("DL123");
        savedFlight.setDepartureDate(now);
        savedFlight.setArrivalDate(now.plusHours(5));
        savedFlight.setStatus(BookingStatus.CONFIRMED);

        when(flightRepository.save(any(Flight.class))).thenReturn(savedFlight);

        // Act
        FlightDTO result = flightService.createFlight(inputDTO);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("JFK", result.getOrigin());
        assertEquals("Delta", result.getAirline()); // Verify airline
        assertEquals("DL123", result.getFlightNumber()); // Verify flight number
        assertEquals(BookingStatus.CONFIRMED, result.getStatus());
    }

    @Test
    void getFlightById_WhenExists_ShouldReturnFlightDTO() {
        // Arrange
        Flight flight = new Flight();
        flight.setId(1L);
        flight.setOrigin("LHR");
        flight.setDestination("DXB"); // Required field
        flight.setAirline("Emirates"); // Required field
        flight.setFlightNumber("EK202"); // Required field
        flight.setDepartureDate(now); // Required field
        flight.setArrivalDate(now.plusHours(7)); // Required field
        flight.setStatus(BookingStatus.CONFIRMED); // Required field

        when(flightRepository.findById(1L)).thenReturn(Optional.of(flight));

        // Act
        Optional<FlightDTO> result = flightService.getFlightById(1L);

        // Assert
        assertTrue(result.isPresent());
        assertEquals("LHR", result.get().getOrigin());
        assertEquals("Emirates", result.get().getAirline()); // Check mapped fields
    }

    @Test
    void updateFlight_WhenNotFound_ShouldThrowException() {
        // Arrange
        when(flightRepository.findById(999L)).thenReturn(Optional.empty());

        FlightDTO updateDTO = new FlightDTO();
        updateDTO.setOrigin("CDG"); // Example valid data
        updateDTO.setDestination("FRA");
        updateDTO.setAirline("Lufthansa");
        updateDTO.setFlightNumber("LH456");
        updateDTO.setDepartureDate(now);
        updateDTO.setArrivalDate(now.plusHours(2));

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> {
            flightService.updateFlight(999L, updateDTO); // Pass valid DTO
        });
    }
}