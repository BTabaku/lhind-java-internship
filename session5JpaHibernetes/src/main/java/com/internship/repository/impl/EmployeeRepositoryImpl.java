package com.internship.repository.impl;

import com.internship.configuration.EntityManagerConfiguration;
import com.internship.model.entity.Employee;
import com.internship.repository.EmployeeRepository;
import com.internship.util.Queries;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class EmployeeRepositoryImpl implements EmployeeRepository {

    private final EntityManager entityManager;

    public EmployeeRepositoryImpl() {
        entityManager = EntityManagerConfiguration.getEntityManager();
    }

    @Override
    public Optional<Employee> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Employee.class, id));
    }

    @Override
    public List<Employee> findAll() {
        TypedQuery<Employee> result = entityManager.createQuery(Queries.FIND_ALL_EMPLOYEES, Employee.class);
        return result.getResultList();
    }

    public List<Employee> findAllNamedQuery(String firstName) {
        Query query = entityManager.createQuery("SELECT e FROM Employee e WHERE e.firstName = :uname");
        query.setParameter("uname", firstName);
        List<Employee> employees = (List<Employee>) query.getResultList();
        TypedQuery<Employee> result = entityManager.createNamedQuery("findByFirstname", Employee.class);
        result.setParameter("uname", firstName);
        return result.getResultList();
    }

    @Override
    public void save(Employee employee) {
        entityManager.getTransaction().begin();
        if (employee.getId() != null) {
            findById(employee.getId()).ifPresent(existingEmployee -> {
                employee.setFirstName(employee.getFirstName());
                employee.setMiddleName(employee.getMiddleName());
                employee.setLastName(employee.getLastName());
            });
        } else {
            entityManager.persist(employee);
        }
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(Employee employee) {
        if (employee.getId() != null) {
            entityManager.getTransaction().begin();
            findById(employee.getId()).ifPresent(entityManager::remove);
            entityManager.getTransaction().commit();
        }
    }

    public Optional<Employee> findByUsername(String username) {
        TypedQuery<Employee> result = entityManager.createNamedQuery("test", Employee.class);
        result.setParameter("username", username);
        return Optional.ofNullable(result.getSingleResult());
    }


}
