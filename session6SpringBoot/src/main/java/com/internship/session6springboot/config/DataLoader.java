package com.internship.session6springboot.config;

import com.internship.session6springboot.entity.*;
import com.internship.session6springboot.enums.BookingStatus;
import com.internship.session6springboot.enums.RoleEnum;
import com.internship.session6springboot.repository.BookingRepository;
import com.internship.session6springboot.repository.FlightRepository;
import com.internship.session6springboot.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
public class DataLoader {

    private final UserRepository userRepository;
    private final FlightRepository flightRepository;
    private final BookingRepository bookingRepository;

    public DataLoader(
            UserRepository userRepository,
            FlightRepository flightRepository,
            BookingRepository bookingRepository
    ) {
        this.userRepository = userRepository;
        this.flightRepository = flightRepository;
        this.bookingRepository = bookingRepository;
    }

    @PostConstruct
    @Transactional
    public void loadInitialData() {
        // Only load data if the database is empty
        if (userRepository.count() == 0 && flightRepository.count() == 0) {
            // Create Users
            User admin = createUser("admin", "admin123", RoleEnum.ADMIN, "John", "Doe", "john@example.com", "+123456789");
            User user1 = createUser("user1", "user123", RoleEnum.USER, "Alice", "Smith", "alice@example.com", "+987654321");

            // Create Flights
            Flight flight1 = createFlight("New York", "London", "Delta", "DL123", LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(1).plusHours(8), BookingStatus.CONFIRMED);
            Flight flight2 = createFlight("Paris", "Dubai", "Emirates", "EK202", LocalDateTime.now().plusDays(2), LocalDateTime.now().plusDays(2).plusHours(7), BookingStatus.PENDING);

            // Create Bookings
            createBooking(admin, flight1, LocalDateTime.now(), BookingStatus.CONFIRMED);
            createBooking(user1, flight2, LocalDateTime.now(), BookingStatus.PENDING);
        }
    }

    private User createUser(
            String username,
            String password,
            RoleEnum role,
            String firstName,
            String lastName,
            String email,
            String phoneNumber
    ) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(role);

        UserDetails details = new UserDetails();
        details.setFirstName(firstName);
        details.setLastName(lastName);
        details.setEmail(email);
        details.setPhoneNumber(phoneNumber);
        user.setUserDetails(details);

        return userRepository.save(user);
    }

    private Flight createFlight(
            String origin,
            String destination,
            String airline,
            String flightNumber,
            LocalDateTime departure,
            LocalDateTime arrival,
            BookingStatus status
    ) {
        Flight flight = new Flight();
        flight.setOrigin(origin);
        flight.setDestination(destination);
        flight.setAirline(airline);
        flight.setFlightNumber(flightNumber);
        flight.setDepartureDate(departure);
        flight.setArrivalDate(arrival);
        flight.setStatus(status);
        return flightRepository.save(flight);
    }

    private void createBooking(User user, Flight flight, LocalDateTime bookingDate, BookingStatus status) {
        Booking booking = new Booking();
        booking.setUser(user);
        booking.setFlight(flight);
        booking.setBookingDate(bookingDate);
        booking.setBookingStatus(status);
        bookingRepository.save(booking);
    }
}