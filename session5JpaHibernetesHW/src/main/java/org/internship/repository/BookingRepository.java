package org.internship.repository;

import org.internship.model.entity.Booking;

import java.util.List;

public interface BookingRepository {
    void save(Booking bookingDetails);

    Booking findById(Long id);

    java.util.List<Booking> findAll();

    void update(Booking bookingDetails);

    void delete(Booking bookingDetails);
}
