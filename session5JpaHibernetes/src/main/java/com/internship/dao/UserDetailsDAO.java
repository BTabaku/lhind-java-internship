package com.internship.dao;

import com.internship.entities.Booking;
import com.internship.entities.UserDetails;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class UserDetailsDAO {

    private EntityManagerFactory emf;
    private EntityManager em;

    public UserDetailsDAO() {
        emf = Persistence.createEntityManagerFactory("internshipPU");
        em = emf.createEntityManager();
    }

    //    save user details
    public void saveUserDetails(UserDetails userDetails) {
        try {
            em.getTransaction().begin();
            em.persist(userDetails);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    public void updateUserDetails(Booking booking) {
        try {
            em.getTransaction().begin();
            em.merge(booking);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public Booking getUserDetails(int id) {
        return em.find(Booking.class, id);
    }

    public void deleteUserDetails(int id) {
        try {
            em.getTransaction().begin();
            Booking booking = em.find(Booking.class, id);
            em.remove(booking);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void close() {
        em.close();
        emf.close();
    }

    public UserDetails getUserDetailsById(int id) {
        return em.find(UserDetails.class, id);
    }
}
