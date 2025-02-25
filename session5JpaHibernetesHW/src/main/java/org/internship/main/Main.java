package org.internship.main;

import org.internship.configuration.EntityManagerConfiguration;
import org.internship.model.entity.Flight;
import org.internship.model.entity.UserDetails;
import org.internship.model.enums.BookingStatus;
import org.internship.model.entity.Booking;
import org.internship.repository.BookingRepository;
import org.internship.repository.imp.BookingRepositoryImpl;
import org.internship.repository.FlightRepository;
import org.internship.repository.UserDetailsRepository;
import org.internship.repository.imp.FlightRepositoryImpl;
import org.internship.repository.imp.UserDetailsRepositoryImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class Main {

   public static void main(String[] args) {

        BookingRepository bookingRepo = new BookingRepositoryImpl();

        // Create a new booking
        Booking booking = new Booking(LocalDateTime.now(), BookingStatus.PENDING);
        bookingRepo.save(booking);
        System.out.println("Booking saved with ID: " + booking.getId());

        // Retrieve booking by ID using Optional
        Optional<Booking> foundBookingOpt = Optional.ofNullable(bookingRepo.findById(booking.getId()));
        if (foundBookingOpt.isPresent()) {
             Booking foundBooking = foundBookingOpt.get();
             System.out.println("Found Booking with status: " + foundBooking.getStatus());

             // Update booking status
             foundBooking.setStatus(BookingStatus.CONFIRMED);
             bookingRepo.update(foundBooking);
             System.out.println("Booking updated.");

             // Retrieve all bookings
             List<Booking> allBookings = bookingRepo.findAll();
             System.out.println("All bookings:");
             allBookings.forEach(b -> System.out.println(" - ID: " + b.getId() + ", Status: " + b.getStatus()));

             // Delete the booking
             bookingRepo.delete(foundBooking);
             System.out.println("Booking deleted.");
        } else {
             System.out.println("Booking not found");
        }

        // Shutdown the EntityManagerFactory when done
        EntityManagerConfiguration.shutdown();

//        FlightRepository flightRepo = new FlightRepositoryImpl();
//
//        // Create a new Booking using the provided constructor.
//        Flight flight = new Flight(
//                null,                           // id (null, so it will be auto-generated)
//                "New York",                       // origin
//                "Brasil",                         // destination
//                "British Airways",                // airline
//                "BA117",                          // flight number
//                LocalDateTime.of(2025, 3, 1, 15, 30),  // departureDate
//                LocalDateTime.of(2025, 3, 1, 23, 45),  // arrivalDate
//                BookingStatus.PENDING             // status
//        );
//
//        flightRepo.save(flight);
//        System.out.println("Booking saved with ID: " + flight.getId());
//
//        // Find booking by ID
//        Flight foundFlight = flightRepo.findById(flight.getId());
//        if (foundFlight != null) {
//            System.out.println("Found Booking: " +
//                    "Origin: " + foundFlight.getOrigin() +
//                    ", Destination: " + foundFlight.getDestination() +
//                    ", Status: " + foundFlight.getStatus());
//        }
//
//        // Update booking status to CONFIRMED
//        foundFlight.setStatus(BookingStatus.CONFIRMED);
//        flightRepo.update(foundFlight);
//        System.out.println("Booking updated.");
//
//        // Retrieve and list all bookings
//        List<Flight> allFlights = flightRepo.findAll();
//        System.out.println("All bookings:");
//        allFlights.forEach(b -> System.out.println(" - ID: " + b.getId() + ", Origin: " + b.getOrigin() +
//                ", Destination: " + b.getDestination() + ", Status: " + b.getStatus()));
//
//        // Delete the booking
////        flightRepo.delete(foundFlight);
////        System.out.println("Booking deleted.");
//
//        // Shutdown the EntityManagerFactory when done
//        EntityManagerConfiguration.shutdown();
//
//
//        // Testing UserDetails Repository
//        UserDetailsRepository userDetailsRepo = new UserDetailsRepositoryImpl();
//
//        // Create a new UserDetails record
//        UserDetails ud = new UserDetails("John", "Doe", "john.doe@example.com", "1234567890");
//        userDetailsRepo.save(ud);
//        System.out.println("UserDetails saved with ID: " + ud.getId());
//
//        // Retrieve UserDetails by ID
//        UserDetails foundUd = userDetailsRepo.findById(ud.getId());
//        if (foundUd != null) {
//             System.out.println("Found UserDetails: " + foundUd.getEmail());
//        }
//
//        // Update UserDetails record
//       foundUd.setPhoneNumber("0987654321");
//        userDetailsRepo.update(foundUd);
//        System.out.println("UserDetails updated.");
//
//        // Retrieve all UserDetails records
//        List<UserDetails> allUserDetails = userDetailsRepo.findAll();
//        System.out.println("All UserDetails records:");
//        allUserDetails.forEach(u -> System.out.println(" - " + u.getEmail()));
//
//        // Delete the UserDetails record
//        userDetailsRepo.delete(foundUd);
//        System.out.println("UserDetails deleted.");
//
//        // Shutdown the EntityManagerFactory when done
//        EntityManagerConfiguration.shutdown();



    }
}
