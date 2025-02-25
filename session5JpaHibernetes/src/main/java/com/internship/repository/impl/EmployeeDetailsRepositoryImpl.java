package com.internship.repository.impl;

import com.internship.configuration.EntityManagerConfiguration;
import com.internship.model.entity.EmployeeDetails;
import com.internship.repository.EmployeeDetailsRepository;

import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class EmployeeDetailsRepositoryImpl implements EmployeeDetailsRepository {

    private final EntityManager entityManager;

    public EmployeeDetailsRepositoryImpl() {
        entityManager = EntityManagerConfiguration.getEntityManager();
    }

    @Override
    public Optional<EmployeeDetails> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public List<EmployeeDetails> findAll() {
        return null;
    }

    @Override
    public void save(EmployeeDetails employeeDetails) {
        entityManager.getTransaction().begin();
        entityManager.persist(employeeDetails);
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(EmployeeDetails employeeDetails) {

    }
}
