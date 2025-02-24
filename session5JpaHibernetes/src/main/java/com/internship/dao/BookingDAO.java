package com.internship.dao;

import com.internship.entities.Booking;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class BookingDAO {

    private EntityManagerFactory emf;
    private EntityManager em;

    public BookingDAO() {
        emf = Persistence.createEntityManagerFactory("internshipPU");
        em = emf.createEntityManager();
    }

    public void addBooking(Booking booking) {
        em.getTransaction().begin();
        em.persist(booking);
        em.getTransaction().commit();
    }

    public void updateBooking(Booking booking) {
        em.getTransaction().begin();
        em.merge(booking);
        em.getTransaction().commit();
    }

    public void deleteBooking(Booking booking) {
        em.getTransaction().begin();
        em.remove(booking);
        em.getTransaction().commit();
    }

    public Booking getBookingById(int id) {
        return em.find(Booking.class, id);
    }

    public void close() {

        em.close();
        emf.close();
    }
}
