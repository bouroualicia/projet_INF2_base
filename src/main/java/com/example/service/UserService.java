package com.example.service;

import java.util.List;
import com.example.dao.UserRepository;
import com.example.domain.User;
import com.example.messaging.AuditProducer; 

public class UserService {

    private final UserRepository userRepository = new UserRepository();
    private final AuditProducer auditProducer = new AuditProducer(); 

    public User createUser(User user) {

        User savedUser = userRepository.save(user);
        auditProducer.sendAuditMessage(
            "USER_CREATED", 
            "User", 
            "Email: " + savedUser.getEmail()
        );

        return savedUser;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User updateUser(User user) {
        User updated = userRepository.update(user);
        if (updated != null) {
            auditProducer.sendAuditMessage("USER_UPDATED", "User", "ID: " + updated.getId());
        }
        return updated;
    }

    public boolean deleteUser(Long id) {
        boolean deleted = userRepository.delete(id);
        if (deleted) {
            auditProducer.sendAuditMessage("USER_DELETED", "User", "ID: " + id);
        }
        return deleted;
    }
}