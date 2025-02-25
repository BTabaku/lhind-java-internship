package com.lhind.repository.impl;

import com.lhind.configuration.EntityManagerConfiguration;
import com.lhind.model.entity.EmployeeDetails;
import com.lhind.repository.EmployeeDetailsRepository;

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
