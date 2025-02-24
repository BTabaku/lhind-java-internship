package com.internship;

import com.internship.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class App {
    public static void main(String[] args) {
        // Obtain the EntityManagerFactory using the persistence unit defined in persistence.xml
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("internshipPU");
        EntityManager em = emf.createEntityManager();

        try {
            // Begin a transaction
            em.getTransaction().begin();

            // Create and persist a new entity instance.
            // Ensure that the User entity exists and has proper JPA annotations.
            User newUser = new User();

            // Set required fields in the newUser entity (adjust according to your entity definition).
            newUser.setUsername("user01");

            // Persist the entity. This triggers Hibernate to create or update the table if needed.
            em.persist(newUser);

            // Commit the transaction so changes are applied to the database
            em.getTransaction().commit();

            System.out.println("Entity persisted and tables updated (if necessary).");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}