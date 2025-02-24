package com.internship.dao;

import com.internship.entities.User;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class UserDAO {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("internshipPU");

    public void createUser(User user) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public User getUserById(int id) {
        EntityManager em = emf.createEntityManager();
        try {
        } finally {
            em.close();
        }
    }

    public void updateUser(User user) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(user);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void deleteUser(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            User user = em.find(User.class, id);
            if (user != null) {
                em.getTransaction().begin();
                em.remove(user);
                em.getTransaction().commit();
            }
        } finally {
            em.close();
        }
    }

    public void close() {
        emf.close();
    }
}