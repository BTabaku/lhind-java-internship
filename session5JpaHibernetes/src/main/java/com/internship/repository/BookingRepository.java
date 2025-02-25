package com.internship.repository;

import com.internship.model.entity.Booking;

import java.util.List;

public interface BookingRepository extends Repository<Booking, Long>{

    List<Booking> findByEmployeeLastName(String lastName);
}
