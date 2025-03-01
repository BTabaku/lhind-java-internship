package com.internship.session6springboot.service.impl;

import com.internship.session6springboot.dto.BookingDTO;
import com.internship.session6springboot.entity.Booking;
import com.internship.session6springboot.entity.Flight;
import com.internship.session6springboot.entity.User;
import com.internship.session6springboot.repository.BookingRepository;
import com.internship.session6springboot.repository.FlightRepository;
import com.internship.session6springboot.repository.UserRepository;
import com.internship.session6springboot.service.BookingService;

import org.springframework.stereotype.Service;

import com.internship.session6springboot.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final FlightRepository flightRepository;
    private final UserRepository userRepository;

    // Constructor injection (or instantiate manually)
    public BookingServiceImpl(BookingRepository bookingRepository, FlightRepository flightRepository, UserRepository userRepository) {
        this.bookingRepository = bookingRepository;
        this.flightRepository = flightRepository;
        this.userRepository = userRepository;
    }

    @Override
    public BookingDTO createBooking(BookingDTO bookingDTO) {
        Booking booking = new Booking();
        booking.setBookingDate(bookingDTO.getBookingDate());
        booking.setBookingStatus(bookingDTO.getBookingStatus());

        Flight flight = flightRepository.findById(bookingDTO.getFlightId())
                .orElseThrow(() -> new ResourceNotFoundException("Flight not found"));
        User user = userRepository.findById(bookingDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        booking.setFlight(flight);
        booking.setUser(user);

        bookingRepository.save(booking);

        // Map to DTO using a mapper (e.g., MapStruct)
        return convertToDTO(booking);
    }

    private BookingDTO convertToDTO(Booking booking) {
        BookingDTO dto = new BookingDTO();
        dto.setId(booking.getId());
        dto.setBookingDate(booking.getBookingDate());
        dto.setBookingStatus(booking.getBookingStatus());
        dto.setFlightId(booking.getFlight().getId());
        dto.setUserId(booking.getUser().getId());
        return dto;
    }

    @Override
    public Optional<BookingDTO> getBookingById(Long id) {
        Optional<Booking> bookingOpt = bookingRepository.findById(id);
        if (bookingOpt.isPresent()) {
            Booking booking = bookingOpt.get();
            BookingDTO dto = new BookingDTO();
            dto.setId(booking.getId());
            dto.setBookingDate(booking.getBookingDate());
            dto.setBookingStatus(booking.getBookingStatus());
            dto.setFlightId(booking.getFlight().getId());
            dto.setUserId(booking.getUser().getId());
            return Optional.of(dto);
        }
        return Optional.empty();
    }


    @Override
    public List<BookingDTO> getAllBookings() {
        List<Booking> bookings = bookingRepository.findAll();
        return bookings.stream().map(booking -> {
            BookingDTO dto = new BookingDTO();
            dto.setId(booking.getId());
            dto.setBookingDate(booking.getBookingDate());
            dto.setBookingStatus(booking.getBookingStatus());
            dto.setFlightId(booking.getFlight().getId());
            dto.setUserId(booking.getUser().getId());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public void updateBooking(Long id, BookingDTO bookingDTO) {
        Optional<Booking> bookingOpt = bookingRepository.findById(id);
        if (bookingOpt.isPresent()) {
            Booking booking = bookingOpt.get();
            booking.setBookingDate(bookingDTO.getBookingDate());
            booking.setBookingStatus(bookingDTO.getBookingStatus());

            // Use save() instead of update()
            bookingRepository.save(booking);
        }
    }

    @Override
    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }

}