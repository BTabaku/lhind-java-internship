package com.internship.session6springboot.util;

public final class Queries {

    private Queries() {
        // Private constructor to prevent instantiation
    }

    // User Queries
    public static final String GET_ALL_USERS = "SELECT u FROM User u";
    public static final String GET_USER_BY_ID = "SELECT u FROM User u WHERE u.id = :id";
    public static final String GET_USER_BY_USERNAME = "SELECT u FROM User u WHERE u.username = :username";
    public static final String SEARCH_USERS = "SELECT u FROM User u WHERE u.username LIKE %:keyword% " + "OR u.userDetails.firstName LIKE %:keyword% " + "OR u.userDetails.lastName LIKE %:keyword%";

    // Flight Queries
    public static final String GET_ALL_FLIGHTS = "SELECT f FROM Flight f";
    public static final String GET_FLIGHT_BY_ID = "SELECT f FROM Flight f WHERE f.id = :id";
    public static final String GET_FLIGHTS_BY_DEPARTURE_AND_ORIGIN = "SELECT f FROM Flight f WHERE f.departureDate >= :start " + "AND f.departureDate < :end AND f.origin = :origin";

    // UserDetails Queries
    public static final String GET_ALL_USER_DETAILS = "SELECT ud FROM UserDetails ud";
    public static final String GET_USER_DETAILS_BY_ID = "SELECT ud FROM UserDetails ud WHERE ud.id = :id";

    // Booking Queries
    public static final String GET_ALL_BOOKINGS = "SELECT b FROM Booking b ORDER BY b.bookingDate DESC";
    public static final String GET_BOOKING_BY_ID = "SELECT b FROM Booking b WHERE b.id = :id";
    public static final String GET_BOOKINGS_BY_USER_ID = "SELECT b FROM Booking b WHERE b.user.id = :userId";
    public static final String GET_BOOKINGS_BY_FLIGHT_ID = "SELECT b FROM Booking b WHERE b.flight.id = :flightId";
    public static final String GET_BOOKING_BY_ID_AND_USER_ID = "SELECT b FROM Booking b WHERE b.id = :bookingId AND b.user.id = :userId";
}
