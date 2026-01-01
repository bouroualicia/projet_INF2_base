package com.example.service;

import com.example.dao.TeamRepository;
import com.example.domain.Team;
import java.util.List;

public class TeamService {

    private final TeamRepository teamRepository = new TeamRepository();

    public Team createTeam(Team team) {
        return teamRepository.save(team);
    }

    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    public Team getTeamById(Long id) {
        return teamRepository.findById(id);
    }

    public Team updateTeam(Team team) {
        return teamRepository.save(team);
    }

    public boolean deleteTeam(Long id) {
        return teamRepository.delete(id);
    }
}