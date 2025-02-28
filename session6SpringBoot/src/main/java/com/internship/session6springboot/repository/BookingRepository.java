package com.internship.session6springboot.repository;

import com.internship.session6springboot.repository.BookingRepository;
import com.internship.session6springboot.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    // Get all bookings ordered by booking date descending (most recent first)
    List<Booking> findAllByOrderByBookingDateDesc();
    List<Booking> findByUserIdOrderByBookingDateDesc(Long userId);
    Optional<Booking> findByIdAndUserId(Long bookingId, Long userId);
    List<Booking> findByFlightId(Long flightId);
}
