package com.lhind.repository;

import com.lhind.model.entity.Booking;

import java.util.List;

public interface BookingRepository extends Repository<Booking, Long>{

    List<Booking> findByEmployeeLastName(String lastName);
}
