package com.example.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "audit_logs")
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String eventType; 
    private String entityName; 
    private String details;    
    private LocalDateTime timestamp;

    public AuditLog() {
        this.timestamp = LocalDateTime.now();
    }

    public AuditLog(String eventType, String entityName, String details) {
        this();
        this.eventType = eventType;
        this.entityName = entityName;
        this.details = details;
    }

    public Long getId() { return id; }
    public String getEventType() { return eventType; }
    public String getEntityName() { return entityName; }
    public String getDetails() { return details; }
    public LocalDateTime getTimestamp() { return timestamp; }
}