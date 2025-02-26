package org.internship.repository.imp;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.HibernateException;

import org.internship.configuration.EntityManagerConfiguration;
import org.internship.model.entity.Booking;
import org.internship.model.entity.Flight;
import org.internship.repository.BookingRepository;
import org.internship.util.Queries;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class BookingRepositoryImpl implements BookingRepository {

    @Override
    public void save(Booking booking) {
        EntityManager em = EntityManagerConfiguration.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(booking);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            EntityManagerConfiguration.closeEntityManager(em);
        }
    }

    @Override
    public Booking findById(Long id) {
        EntityManager em = EntityManagerConfiguration.getEntityManager();
        try {
            TypedQuery<Booking> query = em.createQuery(Queries.GET_BOOKING_BY_ID, Booking.class);
            query.setParameter("id", id);
            return query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            EntityManagerConfiguration.closeEntityManager(em);
        }
    }

    @Override
    public List<Booking> findAll() {
        EntityManager em = EntityManagerConfiguration.getEntityManager();
        try {
            TypedQuery<Booking> query = em.createQuery("from Booking", Booking.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            EntityManagerConfiguration.closeEntityManager(em);
        }
    }

    @Override
    public void update(Booking booking) {
        EntityManager em = EntityManagerConfiguration.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(booking);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            EntityManagerConfiguration.closeEntityManager(em);
        }
    }

    @Override
    public void delete(Booking booking) {
        EntityManager em = EntityManagerConfiguration.getEntityManager();
        try {
            em.getTransaction().begin();
            Booking managedBooking = em.merge(booking);
            em.remove(managedBooking);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            EntityManagerConfiguration.closeEntityManager(em);
        }
    }
}
