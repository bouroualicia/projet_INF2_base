package com.example.service;

import com.example.dao.TeamRepository;
import com.example.domain.Team;
import com.example.messaging.AuditProducer; 
import com.example.persistence.Jpa;
import jakarta.persistence.EntityManager;
import java.util.List;

public class TeamService {

    private final TeamRepository teamRepository = new TeamRepository();
    private final AuditProducer auditProducer = new AuditProducer();
    private EntityManager em = Jpa.getEntityManager();

    public Team createTeam(Team team) {
        try {
            em.getTransaction().begin();
            Team savedTeam = teamRepository.save(team);
            em.getTransaction().commit();

            auditProducer.sendAuditMessage(
                "TEAM_CREATED", 
                "Team", 
                "Name: " + savedTeam.getNameTeam()
            );

            return savedTeam;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        }
    }

    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    public Team getTeamById(Long id) {
        return teamRepository.findById(id);
    }

    public Team updateTeam(Team team) {
        Team updated = teamRepository.save(team);
        auditProducer.sendAuditMessage("TEAM_UPDATED", "Team", "ID: " + team.getId());
        
        return updated;
    }

    public boolean deleteTeam(Long id) {
        boolean deleted = teamRepository.delete(id);
        if (deleted) {
            auditProducer.sendAuditMessage("TEAM_DELETED", "Team", "ID: " + id);
        }
        return deleted;
    }
}