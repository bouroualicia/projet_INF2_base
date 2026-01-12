package com.example.service;

import java.util.List;

import com.example.dao.AuditRepository;
import com.example.domain.AuditLog;

public class AuditService {

    private final AuditRepository repository = new AuditRepository();

    public void logEvent(String type, String entity, String details) {
        AuditLog log = new AuditLog(type, entity, details);
        repository.save(log);
    }

    public List<AuditLog> getAllLogs() {
        return repository.findAll();
    }
}