package org.internship.repository;

import org.internship.model.entity.Flight;

import java.util.List;

public interface FlightRepository {

    void save(Flight flight);
    Flight findById(Long id);
    List<Flight> findAll();

    void update(Flight flight);
    void delete(Flight flight);

}

