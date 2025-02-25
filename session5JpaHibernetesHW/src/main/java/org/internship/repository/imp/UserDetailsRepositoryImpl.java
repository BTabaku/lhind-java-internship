package org.internship.repository.imp;

import org.internship.configuration.EntityManagerConfiguration;
import org.internship.model.entity.UserDetails;
import org.internship.repository.UserDetailsRepository;
import org.internship.util.Queries;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class UserDetailsRepositoryImpl implements UserDetailsRepository {

    @Override
    public void save(UserDetails userDetails) {
        EntityManager em = EntityManagerConfiguration.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(userDetails);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            EntityManagerConfiguration.closeEntityManager(em);
        }
    }

    @Override
    public UserDetails findById(Long id) {
        EntityManager em = EntityManagerConfiguration.getEntityManager();
        try {
            TypedQuery<UserDetails> query = em.createQuery(Queries.GET_USER_DETAILS_BY_ID, UserDetails.class);
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
    public List<UserDetails> findAll() {
        EntityManager em = EntityManagerConfiguration.getEntityManager();
        try {
            TypedQuery<UserDetails> query = em.createQuery(Queries.GET_ALL_USER_DETAILS, UserDetails.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            EntityManagerConfiguration.closeEntityManager(em);
        }
    }

    @Override
    public void update(UserDetails userDetails) {
        EntityManager em = EntityManagerConfiguration.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(userDetails);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            EntityManagerConfiguration.closeEntityManager(em);
        }
    }

    @Override
    public void delete(UserDetails userDetails) {
        EntityManager em = EntityManagerConfiguration.getEntityManager();
        try {
            em.getTransaction().begin();
            UserDetails managed = em.merge(userDetails);
            em.remove(managed);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            EntityManagerConfiguration.closeEntityManager(em);
        }
    }

}
