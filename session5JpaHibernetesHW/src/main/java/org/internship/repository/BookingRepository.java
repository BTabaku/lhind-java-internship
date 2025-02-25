package org.internship.repository;

import org.internship.model.entity.Booking;

import java.util.List;

public interface BookingRepository {

    void save(Booking booking);
    Booking findById(Long id);
    List<Booking> findAll();

    void update(Booking booking);
    void delete(Booking booking);



}

