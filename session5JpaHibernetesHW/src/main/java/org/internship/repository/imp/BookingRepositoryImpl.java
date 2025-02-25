package org.internship.repository.imp;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.HibernateException;

import org.internship.configuration.EntityManagerConfiguration;
import org.internship.model.entity.Booking;
import org.internship.repository.BookingRepository;
import org.internship.util.Queries;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class BookingRepositoryImpl implements BookingRepository {

    private final EntityManager entityManager = EntityManagerConfiguration.getEntityManager();

    @Override
    public void save(Booking bookingDetails) {

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(booking);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null && transaction.getStatus().canRollback()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

    }

    @Override
    public Booking findById(Long id) {
        try {
            TypedQuery<Booking> query = entityManager.createQuery(Queries.GET_BOOKING_BY_ID, Booking.class);
            query.setParameter("id", id);
            return query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Booking> findAll() {
        try {
            TypedQuery<Booking> query = entityManager.createQuery(Queries.GET_ALL_BOOKINGS, Booking.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void update(Booking bookingDetails) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(bookingDetails);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Booking bookingDetails) {

        try {
            entityManager.getTransaction().begin();
            entityManager.remove(bookingDetails);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();

        }
    }
}
