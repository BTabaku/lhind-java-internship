package org.internship.main;

import org.internship.configuration.EntityManagerConfiguration;
import org.internship.model.entity.Booking;
import org.internship.model.enums.BookingStatus;
import org.internship.repository.BookingRepository;
import org.internship.repository.imp.BookingRepositoryImpl;

import java.time.LocalDateTime;
import java.util.List;

public class Main {

   public static void main(String[] args) {
        BookingRepository bookingRepo = new BookingRepositoryImpl();

        // Create a new Booking using the provided constructor.
        Booking booking = new Booking(
                null,                             // id (null, so it will be auto-generated)
                "New York",                       // origin
                "London",                         // destination
                "British Airways",                // airline
                "BA117",                          // flight number
                LocalDateTime.of(2025, 3, 1, 15, 30),  // departureDate
                LocalDateTime.of(2025, 3, 1, 23, 45),  // arrivalDate
                BookingStatus.PENDING             // status
        );

        bookingRepo.save(booking);
        System.out.println("Booking saved with ID: " + booking.getId());

        // Find booking by ID
        Booking foundBooking = bookingRepo.findById(booking.getId());
        if (foundBooking != null) {
            System.out.println("Found Booking: " +
                    "Origin: " + foundBooking.getOrigin() +
                    ", Destination: " + foundBooking.getDestination() +
                    ", Status: " + foundBooking.getStatus());
        }

        // Update booking status to CONFIRMED
        foundBooking.setStatus(BookingStatus.CONFIRMED);
        bookingRepo.update(foundBooking);
        System.out.println("Booking updated.");

        // Retrieve and list all bookings
        List<Booking> allBookings = bookingRepo.findAll();
        System.out.println("All bookings:");
        allBookings.forEach(b -> System.out.println(" - ID: " + b.getId() + ", Origin: " + b.getOrigin() +
                ", Destination: " + b.getDestination() + ", Status: " + b.getStatus()));

        // Delete the booking
        bookingRepo.delete(foundBooking);
        System.out.println("Booking deleted.");
    
        // Shutdown the EntityManagerFactory when done
        EntityManagerConfiguration.shutdown();
    }
}
