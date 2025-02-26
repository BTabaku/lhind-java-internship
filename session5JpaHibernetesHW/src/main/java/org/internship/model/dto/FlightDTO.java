//For creating flights (includes origin, destination, airline, flightNumber, departureDate, arrivalDate)

package org.internship.model.dto;

import java.time.LocalDateTime;

public class FlightDTO {

    private String origin;
    private String destination;
    private String airline;
    private String flightNumber;
    private LocalDateTime departureDate;
    private LocalDateTime arrivalDate;


    public FlightDTO() {
    }

    public FlightDTO(String origin, String destination, String airline, String flightNumber, LocalDateTime departureDate,
                     LocalDateTime arrivalDate) {
        this.origin = origin;
        this.destination = destination;
        this.airline = airline;
        this.flightNumber = flightNumber;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public LocalDateTime getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDateTime departureDate) {
        this.departureDate = departureDate;
    }

    public LocalDateTime getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDateTime arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    @Override
    public String toString() {
        return "FlightDTO{" +
                "origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                ", airline='" + airline + '\'' +
                ", flightNumber='" + flightNumber + '\'' +
                ", departureDate=" + departureDate +
                ", arrivalDate=" + arrivalDate +
                '}';
    }

}
