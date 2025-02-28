package org.internship.mapper;

import org.internship.model.dto.FlightDTO;
import org.internship.model.entity.Flight;
import org.internship.model.enums.BookingStatus;

public class FlightMapper extends AbstractMapper<Flight, FlightDTO> {

    @Override
    public FlightDTO toDTO(Flight flight) {
        if (flight == null) {
            return null;
        }
        return new FlightDTO(
                flight.getId(),
                flight.getOrigin(),
                flight.getDestination(),
                flight.getAirline(),
                flight.getFlightNumber(),
                flight.getDepartureDate(),
                flight.getArrivalDate(),
                flight.getStatus().name()  // converting enum to String
        );
    }

    @Override
    public Flight toEntity(FlightDTO dto) {
        if (dto == null) {
            return null;
        }
        Flight flight = new Flight();
        flight.setId(dto.getId());
        flight.setOrigin(dto.getOrigin());
        flight.setDestination(dto.getDestination());
        flight.setAirline(dto.getAirline());
        flight.setFlightNumber(dto.getFlightNumber());
        flight.setDepartureDate(dto.getDepartureDate());
        flight.setArrivalDate(dto.getArrivalDate());
        // Convert status from String to enum (ensure dto.getStatus() is not null)
        if (dto.getStatus() != null) {
            flight.setStatus(BookingStatus.valueOf(dto.getStatus()));
        } else {
            // Alternatively, you could set a default value:
            flight.setStatus(BookingStatus.PENDING);
        }
        return flight;
    }
}
