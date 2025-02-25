package org.internship.util;

public final class Queries {
    public static final String GET_ALL_USERS = "SELECT u FROM User u";
    public static final String GET_USER_BY_ID = "SELECT u FROM User u WHERE u.id = :id";
}