package com.internship.session6springboot.dto;

import com.internship.session6springboot.enums.BookingStatus;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingDTO {
    private Long id;
    private Long flightId;
    private Long userId;
    private LocalDateTime bookingDate;
    private BookingStatus bookingStatus;
}