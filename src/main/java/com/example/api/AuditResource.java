package com.example.api;

import java.util.List;

import com.example.domain.AuditLog;
import com.example.service.AuditService;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/audit")
@Produces(MediaType.APPLICATION_JSON)
public class AuditResource {

    private final AuditService auditService = new AuditService();

    @GET
    public List<AuditLog> getAuditHistory() {
        return auditService.getAllLogs();
    }
}