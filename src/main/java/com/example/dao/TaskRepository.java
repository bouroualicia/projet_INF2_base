package com.example.dao;

import com.example.domain.Task;
import com.example.domain.User;
import com.example.domain.Team;
import com.example.persistence.Jpa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.List;

public class TaskRepository {

    public Task save(Task task, Long userId, Long teamId) {
        EntityManager em = Jpa.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
    
            if (userId != null) {
                User user = em.find(User.class, userId);
                if (user != null) task.setUser(user);
            }
            if (teamId != null) {
                Team team = em.find(Team.class, teamId);
                if (team != null) task.setTeam(team);
            }
    
            if (task.getIdTask() == null) {
                em.persist(task);
            } else {
                task = em.merge(task);
            }

            em.flush(); 
            
            tx.commit();

            return em.createQuery(
                    "SELECT t FROM Task t " +
                    "LEFT JOIN FETCH t.user " +
                    "LEFT JOIN FETCH t.team " +
                    "WHERE t.idTask = :id", Task.class)
                    .setParameter("id", task.getIdTask())
                    .getSingleResult();
            
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            em.close(); 
        }
    }

    public List<Task> findAll() {
        EntityManager em = Jpa.getEntityManager();
        try {
            return em.createQuery(
                    "SELECT DISTINCT t FROM Task t " +
                    "LEFT JOIN FETCH t.user " +
                    "LEFT JOIN FETCH t.team",
                    Task.class).getResultList();
        } finally {
            em.close();
        }
    }

    public Task findById(Long id) {
        EntityManager em = Jpa.getEntityManager();
        try {
            return em.createQuery(
                            "SELECT t FROM Task t " +
                            "LEFT JOIN FETCH t.user " +
                            "LEFT JOIN FETCH t.team tm " +
                            "LEFT JOIN FETCH tm.owner " +
                            "WHERE t.idTask = :id", Task.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public boolean delete(Long id) {
        EntityManager em = Jpa.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Task task = em.find(Task.class, id);
            if (task != null) {
                em.remove(task);
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