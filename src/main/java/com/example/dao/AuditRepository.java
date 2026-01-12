package com.example.dao;

import java.util.List;

import com.example.domain.AuditLog;
import com.example.persistence.Jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class AuditRepository {

    public void save(AuditLog log) {
        EntityManager em = Jpa.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(log);
            tx.commit();
        } finally {
            em.close();
        }
    }

    public List<AuditLog> findAll() {
        EntityManager em = Jpa.getEntityManager();
        try {
            return em.createQuery("SELECT a FROM AuditLog a ORDER BY a.timestamp DESC", AuditLog.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }
}