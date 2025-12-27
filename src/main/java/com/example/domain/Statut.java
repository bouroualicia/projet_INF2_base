package com.example.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "statuts")
public class Statut {

    @Id @GeneratedValue
    private Long idStatut;

    private String nameStatut;
    private String priorite;
    private String date;

    private Long idTask;
    private Long idUser;
    private Long idTeam;

    public Statut() {}

    public Statut(String nameStatut, String priorite, String date, Long idTask, Long idUser, Long idTeam) {
        this.nameStatut = nameStatut;
        this.priorite = priorite;
        this.date = date;
        this.idTask = idTask;
        this.idUser = idUser;
        this.idTeam = idTeam;
    }

    public Long getIdStatut() { return idStatut; }
    public String getNameStatut() { return nameStatut; }
    public String getPriorite() { return priorite; }
    public String getDate() { return date; }
    public Long getIdTask() { return idTask; }
    public Long getIdUser() { return idUser; }
    public Long getIdTeam() { return idTeam; }

    public void setNameStatut(String nameStatut) { this.nameStatut = nameStatut; }
    public void setPriorite(String priorite) { this.priorite = priorite; }
    public void setDate(String date) { this.date = date; }
    public void setIdTask(Long idTask) { this.idTask = idTask; }
    public void setIdUser(Long idUser) { this.idUser = idUser; }
    public void setIdTeam(Long idTeam) { this.idTeam = idTeam; }
}
