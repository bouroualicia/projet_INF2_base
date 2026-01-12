package com.example.service;

import java.util.List;

import com.example.dao.UserRepository;
import com.example.domain.User;
import com.example.messaging.UserCreatedProducer;
import com.example.persistence.Jpa;

import jakarta.persistence.EntityManager;

public class UserService {

    private EntityManager em = Jpa.getEntityManager();
    private UserCreatedProducer producer = new UserCreatedProducer();

    public User createUser(User user) {
        try {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
            producer.sendUserCreatedEvent(user.getId(), user.getEmail());

            return user;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }
    }

    public List<User> getAllUsers() {
        return em.createQuery("SELECT u FROM User u", User.class)
                .getResultList();
    }

    public User getUserById(Long id) {
        return em.find(User.class, id);
    }

    public User updateUser(User user) {
        return userRepository.update(user);
    }

    private final UserRepository userRepository = new UserRepository();

    public boolean deleteUser(Long id) {
        return userRepository.delete(id);
    }

}