package com.internship.repository;

import com.internship.model.entity.Booking;

import java.util.List;
import java.util.Optional;

public interface BookingRepository extends Repository<Booking, Long>{

    // Remove this line
    // void update(Booking booking);

    // Keep other methods
    List<Booking> findAllByOrderByBookingDateDesc();
    List<Booking> findByUserIdOrderByBookingDateDesc(Long userId);
    Optional<Booking> findByIdAndUserId(Long bookingId, Long userId);
    List<Booking> findByFlightId(Long flightId);
}
