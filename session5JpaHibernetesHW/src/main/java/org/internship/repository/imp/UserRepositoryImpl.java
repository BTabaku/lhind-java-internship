package org.internship.repository.imp;

import org.internship.configuration.EntityManagerConfiguration;
import org.internship.model.entity.User;
import org.internship.repository.UserRepository;
import org.internship.util.Queries;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;


public class UserRepositoryImpl implements UserRepository {
    @Override
    public void save(User user) {
        EntityManager entityManager = EntityManagerConfiguration.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public User findById(Long id) {
        EntityManager em = EntityManagerConfiguration.getEntityManager();
        try {
            TypedQuery<User> query = em.createQuery(Queries.GET_USER_BY_ID, User.class);
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
    public List<User> findAll() {
        EntityManager em = EntityManagerConfiguration.getEntityManager();
        try {
            TypedQuery<User> query = em.createQuery(Queries.GET_ALL_USERS, User.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            EntityManagerConfiguration.closeEntityManager(em);
        }
    }
    
    @Override
    public void update(User user) {
        EntityManager em = EntityManagerConfiguration.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(user);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            EntityManagerConfiguration.closeEntityManager(em);
        }
    }

    @Override
    public void delete(User user) {
        EntityManager em = EntityManagerConfiguration.getEntityManager();
        try {
            em.getTransaction().begin();
            em.remove(user);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            EntityManagerConfiguration.closeEntityManager(em);
        }
    }

}
