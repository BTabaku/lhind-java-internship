package com.internship.dao;

import com.internship.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import java.util.List;

public class UserDAO {
    private EntityManagerFactory emf;
    private EntityManager em;

    public UserDAO() {
        emf = Persistence.createEntityManagerFactory("internshipPU");
        em = emf.createEntityManager();
    }

    public User createUser(User user) {
        try {
            if (findByUsername(user.getUsername())) {
                throw new IllegalArgumentException("Username already exists");
            }

            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
            return user;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        }
    }

    private boolean findByUsername(String username) {
        try {
            em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)
                    .setParameter("username", username)
                    .getSingleResult();
            return true;
        } catch (NoResultException e) {
            return false;
        }
    }

    public User getUserById(int id) {
        return em.find(User.class, id);
    }

    public List<User> getAllUsers() {
        return em.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    public User updateUser(User user) {
        try {
            em.getTransaction().begin();
            em.merge(user);
            em.getTransaction().commit();
            return user;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        }
    }

    public void deleteUser(int id) {
        try {
            em.getTransaction().begin();
            User user = em.find(User.class, id);
            if (user != null) {
                em.remove(user);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        }
    }

    public void close() {
        if (em != null && em.isOpen()) {
            em.close();
        }
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}