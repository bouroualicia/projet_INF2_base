package com.example.dao;

import com.example.domain.Statut;
import com.example.persistence.Jpa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public class StatutRepository {

    public void save(Statut statut) {
        EntityManager em = Jpa.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.persist(statut);
            tx.commit();
        } finally {
            em.close();
        }
    }

    public List<Statut> findAll() {
        EntityManager em = Jpa.getEntityManager();
        try {
            return em.createQuery("SELECT s FROM Statut s", Statut.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public Statut findById(Long id) {
        EntityManager em = Jpa.getEntityManager();
        try {
            return em.find(Statut.class, id);
        } finally {
            em.close();
        }
    }

    public Statut update(Long id, Statut newStatut) {
        EntityManager em = Jpa.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            Statut statut = em.find(Statut.class, id);
            if (statut == null) return null;

            statut.setNameStatut(newStatut.getNameStatut());
            statut.setPriorite(newStatut.getPriorite());
            statut.setDate(newStatut.getDate());
            statut.setIdTask(newStatut.getIdTask());
            statut.setIdUser(newStatut.getIdUser());
            statut.setIdTeam(newStatut.getIdTeam());

            tx.commit();
            return statut;
        } finally {
            em.close();
        }
    }

    public boolean delete(Long id) {
        EntityManager em = Jpa.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            Statut statut = em.find(Statut.class, id);
            if (statut == null) return false;

            em.remove(statut);
            tx.commit();
            return true;
        } finally {
            em.close();
        }
    }
}
