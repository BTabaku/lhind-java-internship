package com.internship.session6springboot.entity;

import jakarta.persistence.*;
import com.internship.session6springboot.enums.BookingStatus;

import java.util.List;

import java.time.LocalDateTime;

@Entity
@Table(name = "booking")
public class Booking {

    @ManyToOne
    @JoinColumn(name = "flight_id")  // Foreign key in the booking table
    private Flight flight;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "booking_date")
    private LocalDateTime bookingDate;

    @Column(name = "booking_status")
    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;


    public Booking() {
    }

    public Booking(LocalDateTime bookingDate, BookingStatus bookingStatus) {
        this.bookingDate = bookingDate;
        this.bookingStatus = bookingStatus;
    }


    public Long getId() {
        return id;
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

    public void setStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public BookingStatus getStatus() {
        return bookingStatus;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }
}