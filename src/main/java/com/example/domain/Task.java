package com.example.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "tasks")
public class Task {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTask;

    private String nameTask;
    private String description;
    private String date;

    private Long idUser;   // créateur ou assigné
    private Long idTeam;
    private Long idStatut;

    public Task() {}

    public Task(String nameTask, String description, String date, Long idUser, Long idTeam, Long idStatut) {
        this.nameTask = nameTask;
        this.description = description;
        this.date = date;
        this.idUser = idUser;
        this.idTeam = idTeam;
        this.idStatut = idStatut;
    }

    public Long getIdTask() { return idTask; }
    public String getNameTask() { return nameTask; }
    public String getDescription() { return description; }
    public String getDate() { return date; }
    public Long getIdUser() { return idUser; }
    public Long getIdTeam() { return idTeam; }
    public Long getIdStatut() { return idStatut; }

    public void setNameTask(String nameTask) { this.nameTask = nameTask; }
    public void setDescription(String description) { this.description = description; }
    public void setDate(String date) { this.date = date; }
    public void setIdUser(Long idUser) { this.idUser = idUser; }
    public void setIdTeam(Long idTeam) { this.idTeam = idTeam; }
    public void setIdStatut(Long idStatut) { this.idStatut = idStatut; }

    public void setIdTask(Long idTask) {
    this.idTask = idTask;
}
}
