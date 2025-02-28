package com.internship.session6springboot.service;

import com.internship.session6springboot.dto.BookingDTO;

import java.util.List;
import java.util.Optional;

public interface BookingService {
    BookingDTO createBooking(BookingDTO bookingDTO);
    Optional<BookingDTO> getBookingById(Long id);
    List<BookingDTO> getAllBookings();
    void updateBooking(Long id, BookingDTO bookingDTO);
    void deleteBooking(Long id);
}
