package org.internship.repository.imp;

import org.internship.configuration.EntityManagerConfiguration;
import org.internship.model.entity.Flight;
import org.internship.repository.FlightRepository;
import org.internship.util.Queries;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class FlightRepositoryImpl implements FlightRepository {
    @Override
    public void save(Flight flight) {
        EntityManager em = EntityManagerConfiguration.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(flight);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            EntityManagerConfiguration.closeEntityManager(em);
        }
    }

    @Override
    public Flight findById(Long id) {
        EntityManager em = EntityManagerConfiguration.getEntityManager();
        try {
            TypedQuery<Flight> query = em.createQuery(Queries.GET_FLIGHTS_BY_ID, Flight.class);
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
    public List<Flight> findAll() {
        EntityManager em = EntityManagerConfiguration.getEntityManager();
        try {
            TypedQuery<Flight> query = em.createQuery(Queries.GET_ALL_FLIGHTS, Flight.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            EntityManagerConfiguration.closeEntityManager(em);
        }
    }

    @Override
    public void update(Flight flight) {
        EntityManager em = EntityManagerConfiguration.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(flight);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            EntityManagerConfiguration.closeEntityManager(em);
        }
    }

    @Override
    public void delete(Flight flight) {
        EntityManager em = EntityManagerConfiguration.getEntityManager();
        try {
            em.getTransaction().begin();
            // Ensure the booking is managed before removal
            Flight managedFlight = em.merge(flight);
            em.remove(managedFlight);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            EntityManagerConfiguration.closeEntityManager(em);
        }
    }


}
