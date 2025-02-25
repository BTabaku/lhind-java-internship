package org.internship.repository.imp;

import org.internship.configuration.EntityManagerConfiguration;
import org.internship.model.entity.Flight;
import org.internship.repository.BookingRepository;
import org.internship.util.Queries;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class BookingRepositoryImpl implements BookingRepository {

    private EntityManager entityManager = EntityManagerConfiguration.getEntityManager();

    @Override
    public List<Flight> getFlights() {
        TypedQuery<Flight> query = entityManager.createQuery(Queries.GET_ALL_BOOKINGS, Flight.class);
        return query.getResultList();
    }

    @Override
    public Flight getFlightById(Long id) {
        return entityManager.find(Flight.class, id);
    }

    @Override
    public void saveFlight(Flight flight) {
        entityManager.getTransaction().begin();
        entityManager.persist(flight);
        entityManager.getTransaction().commit();
    }

    @Override
    public void updateFlight(Flight flight) {
        entityManager.getTransaction().begin();
        entityManager.merge(flight);
        entityManager.getTransaction().commit();
    }

    @Override
    public void deleteFlight(Long id) {
        Flight flight = entityManager.find(Flight.class, id);
        entityManager.getTransaction().begin();
        entityManager.remove(flight);
        entityManager.getTransaction().commit();
    }

}
