package com.internship;

import com.internship.dao.*;
import com.internship.entities.*;

public class App {
    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();
        FlightDAO flightDAO = new FlightDAO();
        BookingDAO bookingDAO = new BookingDAO();
        UserDetailsDAO userDetailsDAO = new UserDetailsDAO();

        // Create dummy users
        User user1 = new User("john", "pass123", "customer");
        User user2 = new User("emma", "pass456", "admin");
        userDAO.createUser(user1);
        userDAO.createUser(user2);

        // Create user details
        UserDetails details1 = new UserDetails("John", "Doe", "john@test.com", "555-1234", user1);
        UserDetails details2 = new UserDetails("Emma", "Smith", "emma@test.com", "555-5678", user2);
        userDetailsDAO.saveUserDetails(details1);
        userDetailsDAO.saveUserDetails(details2);

        // Create flights
        Flight flight1 = new Flight();
        flight1.setOrigin("JFK");
        flight1.setDestination("LAX");
        flight1.setAirline("Delta");
        flight1.setFlightNumber("DL123");
        flight1.setDepartureDate("2024-03-20");
        flight1.setArrivalDate("2024-03-20");
        flight1.setStatus("On Time");

        Flight flight2 = new Flight();
        flight2.setOrigin("LAX");
        flight2.setDestination("ORD");
        flight2.setAirline("United");
        flight2.setFlightNumber("UA456");
        flight2.setDepartureDate("2024-03-21");
        flight2.setArrivalDate("2024-03-21");
        flight2.setStatus("Delayed");

        flightDAO.createFlight(flight1);
        flightDAO.createFlight(flight2);

        // Create bookings
        Booking booking1 = new Booking();
        booking1.setUser(user1);
        booking1.setFlight(flight1);
        booking1.setBookingDate("2024-03-15");
        booking1.setBookingTime("10:00 AM");

        Booking booking2 = new Booking();
        booking2.setUser(user2);
        booking2.setFlight(flight2);
        booking2.setBookingDate("2024-03-16");
        booking2.setBookingTime("02:30 PM");

        bookingDAO.addBooking(booking1);
        bookingDAO.addBooking(booking2);

        // Cleanup
        userDAO.close();
        flightDAO.close();
        bookingDAO.close();
        userDetailsDAO.close();
    }
}