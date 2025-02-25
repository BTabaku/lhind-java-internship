package org.internship.util;

public final class Queries {

    //    User queries
    public static final String GET_ALL_USERS = "SELECT u FROM User u";
    public static final String GET_USER_BY_ID = "SELECT u FROM User u WHERE u.id = :id";

    //    Flight queries
    public static final String GET_FLIGHTS_BY_ID = "SELECT b FROM Flight b WHERE b.id = :id";
    public static final String GET_ALL_FLIGHTS = "SELECT b FROM Flight b";

    //    UserDetails queries
    public static final String GET_USER_DETAILS_BY_ID = "SELECT ud FROM UserDetails ud WHERE ud.id = :id";
    public static final String GET_ALL_USER_DETAILS = "SELECT ud FROM UserDetails ud";

//    booking queries

    public static final String GET_BOOKING_BY_ID = "SELECT b FROM Booking b WHERE b.id = :id";
    public static final String GET_ALL_BOOKINGS = "SELECT b FROM Booking b";

}