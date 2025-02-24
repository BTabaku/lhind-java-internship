package com.internship.dao;

import com.internship.entities.Flight;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class FlightDAO {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("internshipPU");

    public void createFlight(Flight flight) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(flight);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public Flight getFlightById(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Flight.class, id);
        } finally {
            em.close();
        }
    }

    public List<Flight> getAllFlights() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT f FROM Flight f", Flight.class).getResultList();
        } finally {
            em.close();
        }
    }

    public void updateFlight(Flight flight) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(flight);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void deleteFlight(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Flight flight = em.find(Flight.class, id);
            if (flight != null) em.remove(flight);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void close() {
        emf.close();
    }
}