package com.example.dao;

import com.example.domain.User;
import com.example.persistence.Jpa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class UserRepository {

    public User save(User user) {
        EntityManager em = Jpa.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        em.persist(user);
        tx.commit();

        em.close();
        return user;
    }

    public User findById(Long id) {
        EntityManager em = Jpa.getEntityManager();
        User user = em.find(User.class, id);
        em.close();
        return user;
    }

    public User update(User user) {
        EntityManager em = Jpa.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            User updatedUser = em.merge(user);
            tx.commit();
            return updatedUser;
        } finally {
            em.close();
        }
    }

    public boolean delete(Long id) {
        EntityManager em = Jpa.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            User user = em.find(User.class, id);
            if (user != null) {
                em.remove(user);
                tx.commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }
}
