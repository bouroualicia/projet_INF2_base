package com.example.dao;

import com.example.domain.Task;
import com.example.persistence.Jpa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.List;

public class TaskRepository {

    /**
     * Enregistre une nouvelle tâche ou met à jour une tâche existante.
     */
    public Task save(Task task) {
        EntityManager em = Jpa.getEntityManager(); // Utilise votre classe Jpa
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            // Si l'id est null, on persiste, sinon on fusionne (update)
            if (task.getIdTask() == null) {
                em.persist(task);
            } else {
                task = em.merge(task);
            }
            tx.commit();
            return task;
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace(); // Pour voir l'erreur exacte dans votre console Grizzly
            throw e;
        } finally {
            em.close(); // Important pour éviter les fuites de mémoire
        }
    }

    /**
     * Récupère toutes les tâches de la base de données.
     */
    public List<Task> findAll() {
        EntityManager em = Jpa.getEntityManager();
        try {
            return em.createQuery("SELECT t FROM Task t", Task.class).getResultList();
        } finally {
            em.close();
        }
    }

    /**
     * Trouve une tâche par son identifiant.
     */
    public Task findById(Long id) {
        EntityManager em = Jpa.getEntityManager();
        try {
            return em.find(Task.class, id);
        } finally {
            em.close();
        }
    }

    public void delete(Long id) {
    EntityManager em = Jpa.getEntityManager();
    try {
        em.getTransaction().begin();
        Task task = em.find(Task.class, id);
        if (task != null) em.remove(task);
        em.getTransaction().commit();
    } finally {
        em.close();
    }
}
}