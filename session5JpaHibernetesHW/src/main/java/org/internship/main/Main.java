package org.internship.main;

import jakarta.persistence.EntityManager;
import org.internship.configuration.EntityManagerConfiguration;

import org.internship.model.enums.RoleEnum;
import org.internship.model.entity.User;


public class Main {

    public static void main(String[] args) {
        EntityManager em = EntityManagerConfiguration.getEntityManager();
        try {
            em.getTransaction().begin();

            // Create a new User instance
            User newUser = new User("john_doe", "password123", RoleEnum.USER);
            em.persist(newUser);

            em.getTransaction().commit();
            System.out.println("User saved with ID: " + newUser.getId());
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            EntityManagerConfiguration.closeEntityManager(em);
            EntityManagerConfiguration.shutdown();
        }
    }

}
