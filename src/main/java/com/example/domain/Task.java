package com.example.domain;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tasks")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) //pour régler pb du Lazy Loading
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTask;

    private String nameTask;
    private String description;
    private String date;

    //La tâche est assignée à UN utilisateur
    @ManyToOne(fetch = FetchType.LAZY) // Indispensable pour le barème
    @JoinColumn(name = "user_id")
    private User user;

    //La tâche appartient à UNE équipe
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    public Task() {}

    // Getters et Setters
    public Long getIdTask() { return idTask; }
    public void setIdTask(Long idTask) { this.idTask = idTask; }
    public String getNameTask() { return nameTask; }
    public void setNameTask(String nameTask) { this.nameTask = nameTask; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public Team getTeam() { return team; }
    public void setTeam(Team team) { this.team = team; }
}