package com.internship.session6springboot.dto;

import com.internship.session6springboot.enums.BookingStatus;

import java.time.LocalDateTime;

public class BookingDTO {
    private Long id;
    private Long flightId;
    private Long userId;
    private LocalDateTime bookingDate;
    private BookingStatus bookingStatus;

    // Constructors
    public BookingDTO() {}

    public BookingDTO(Long id, Long flightId, Long userId, LocalDateTime bookingDate, BookingStatus bookingStatus) {
        this.id = id;
        this.flightId = flightId;
        this.userId = userId;
        this.bookingDate = bookingDate;
        this.bookingStatus = bookingStatus;
    }

    // Getters and Setters
    // ... (generate these using your IDE)

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getFlightId() {
        return flightId;
    }
    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public LocalDateTime getBookingDate() {
        return bookingDate;
    }
    public void setBookingDate(LocalDateTime bookingDate) {
        this.bookingDate = bookingDate;
    }
    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }
    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }
}