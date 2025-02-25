package org.internship.util;

public final class Queries {

//    User queries
    public static final String GET_ALL_USERS = "SELECT u FROM User u";
    public static final String GET_USER_BY_ID = "SELECT u FROM User u WHERE u.id = :id";

//    Booking queries
    public static final String GET_BOOKING_BY_ID = "SELECT b FROM Booking b WHERE b.id = :id";
    public static final String GET_ALL_BOOKINGS = "SELECT b FROM Booking b";
}