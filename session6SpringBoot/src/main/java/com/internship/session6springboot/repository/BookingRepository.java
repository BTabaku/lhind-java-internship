package com.internship.session6springboot.repository;

import com.internship.session6springboot.repository.BookingRepository;
import com.internship.session6springboot.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    // Get all bookings ordered by booking date descending (most recent first)
    List<Booking> findAllByOrderByBookingDateDesc();

    // Get all bookings for a given user
    List<Booking> findByUserIdOrderByBookingDateDesc(Long userId);

    // Get a specific booking for a specific user
    Optional<Booking> findByIdAndUserId(Long bookingId, Long userId);

    // Get all bookings on a specific flight
    List<Booking> findByFlightId(Long flightId);

    void update(Booking booking);
}
