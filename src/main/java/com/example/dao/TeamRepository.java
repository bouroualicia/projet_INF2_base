package com.example.dao;

import java.util.List;
import com.example.domain.Team;
import com.example.persistence.Jpa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class TeamRepository {

    public Team save(Team team) {
        EntityManager em = Jpa.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            if (team.getId() == null) {
                em.persist(team);
            } else {
                team = em.merge(team);
            }
            tx.commit();

            return this.findById(team.getId());
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public List<Team> findAll() {
        EntityManager em = Jpa.getEntityManager();
        try {
            return em.createQuery(
                "SELECT DISTINCT t FROM Team t LEFT JOIN FETCH t.owner", 
                Team.class)
                .getResultList();
        } finally {
            em.close();
        }
    }


    public Team findById(Long id) {
        EntityManager em = Jpa.getEntityManager();
        try {
            return em.createQuery(
                "SELECT t FROM Team t LEFT JOIN FETCH t.owner WHERE t.id = :id",
                Team.class)
                .setParameter("id", id)
                .getSingleResult();
        } catch (Exception e) {
            return null; 
        } finally {
            em.close();
        }
    }

    public Team update(Team team) {
        return save(team); 
    }

    public boolean delete(Long id) {
        EntityManager em = Jpa.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Team team = em.find(Team.class, id);
            if (team != null) {
                em.remove(team);
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