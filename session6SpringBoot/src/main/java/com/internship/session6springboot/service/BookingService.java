package com.internship.session6springboot.service;

import com.internship.session6springboot.dto.BookingDTO;
import com.internship.session6springboot.dto.BookingResponseDTO;

public interface BookingService {
    BookingResponseDTO createBooking(BookingRequestDTO bookingRequestDTO);
    Optional<BookingResponseDTO> getBookingById(Long id);
    List<BookingResponseDTO> getAllBookings();
    void updateBooking(Long id, BookingRequestDTO bookingRequestDTO);
    void deleteBooking(Long id);
}