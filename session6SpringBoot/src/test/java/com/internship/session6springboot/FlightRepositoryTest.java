package com.internship.session6springboot;

import com.internship.session6springboot.entity.Flight;
import com.internship.session6springboot.enums.BookingStatus;
import com.internship.session6springboot.repository.FlightRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest  // Focuses only on JPA components
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // Use MySQL, not an embedded database
@TestPropertySource(locations = "classpath:application.properties") // Ensure test configuration is loaded
class FlightRepositoryTest {

    @Autowired
    private FlightRepository flightRepository;

    @Test
    void saveFlight_ShouldPersistCorrectly() {
        LocalDateTime departureTime = LocalDateTime.now();
        LocalDateTime arrivalTime = LocalDateTime.now().plusHours(2);

        // Arrange
        Flight flight = new Flight();
        flight.setOrigin("CDG");                    // Changed from "New York" to "CDG"
        flight.setDestination("FRA");               // Changed from "London" to "FRA" to match assertion
        flight.setAirline("Airline Name");
        flight.setFlightNumber("FL123");
        flight.setDepartureDate(departureTime);
        flight.setArrivalDate(arrivalTime);
        flight.setStatus(BookingStatus.PENDING);

        // Act
        Flight savedFlight = flightRepository.save(flight);

        // Assert
        assertThat(savedFlight).isNotNull();
        assertThat(savedFlight.getId()).isNotNull();
        assertThat(savedFlight.getOrigin()).isEqualTo("CDG");
        assertThat(savedFlight.getDestination()).isEqualTo("FRA");
        assertThat(savedFlight.getDepartureDate()).isEqualTo(departureTime);
        assertThat(savedFlight.getStatus()).isEqualTo(BookingStatus.PENDING);
    }

   @Test
    void findById_ShouldReturnSavedFlight() {
        LocalDateTime departureTime = LocalDateTime.now();
        LocalDateTime arrivalTime = LocalDateTime.now().plusHours(2);

        // Arrange
        Flight flight = new Flight();
        flight.setOrigin("New York");
        flight.setDestination("London");
        flight.setAirline("Airline Name");
        flight.setFlightNumber("FL123");
        flight.setDepartureDate(departureTime);
        flight.setArrivalDate(arrivalTime);
        flight.setStatus(BookingStatus.PENDING);

        // Save the flight first
        Flight savedFlight = flightRepository.save(flight);

        // Act
        Flight foundFlight = flightRepository.findById(savedFlight.getId()).orElse(null);

        // Assert
        assertThat(foundFlight).isNotNull();
        assertThat(foundFlight.getId()).isEqualTo(savedFlight.getId());
    }
}
