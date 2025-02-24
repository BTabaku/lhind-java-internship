package com.internship.entities;

import javax.persistence.*;

@Entity
@Table(name = "booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "booking_date")
    private String bookingDate;

    @Column(name = "status")
    private String bookingTime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;

    public Booking() {
    }

    public Booking(String bookingDate, String bookingTime, User user, Flight flight) {
        this.bookingDate = bookingDate;
        this.bookingTime = bookingTime;
        this.user = user;
        this.flight = flight;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(String bookingTime) {
        this.bookingTime = bookingTime;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }
}
