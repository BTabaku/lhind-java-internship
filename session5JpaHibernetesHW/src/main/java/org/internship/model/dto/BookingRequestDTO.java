//For creating bookings (includes flightId, userId, bookingDate)

package org.internship.model.dto;

import java.time.LocalDateTime;

public class BookingRequestDTO {
    private Long flightId;
    private Long userId;
    private LocalDateTime bookingDate;

    public BookingRequestDTO() {
    }

    public BookingRequestDTO(Long flightId, Long userId, LocalDateTime bookingDate) {
        this.flightId = flightId;
        this.userId = userId;
        this.bookingDate = bookingDate;
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

    @Override
    public String toString() {
        return "BookingRequestDTO{" +
                "flightId=" + flightId +
                ", userId=" + userId +
                ", bookingDate=" + bookingDate +
                '}';
    }
}
