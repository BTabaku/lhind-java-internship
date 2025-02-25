package org.internship.model.entity;

import jakarta.persistence.*;
import org.internship.model.enums.BookingStatus;

import java.time.LocalDateTime;

@Entity
@Table(name = "booking")
public class Booking {

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
}
