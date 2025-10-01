package org.bankservice.dao;

import org.bankservice.persistence.EntityManagerFactoryProvider;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class BaseDao<T> {
    private final Class<T> entityClass;

    public BaseDao(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public T findById(Object id) {
        EntityManager em = EntityManagerFactoryProvider.getEntityManager();
        try {
            return em.find(entityClass, id);
        } finally {
            em.close();
        }
    }

    public void persist(T entity) {
        EntityManager em = EntityManagerFactoryProvider.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(entity);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    public T merge(T entity) {
        EntityManager em = EntityManagerFactoryProvider.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            T merged = em.merge(entity);
            tx.commit();
            return merged;
        } catch (RuntimeException e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    public void remove(T entity) {
        EntityManager em = EntityManagerFactoryProvider.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            T merged = em.merge(entity);
            em.remove(merged);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }
}