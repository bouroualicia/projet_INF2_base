package com.example.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "teams")
public class Team {

    @Id @GeneratedValue
    private Long id;

    private String nameTeam;

    // id_user du diagramme = propriétaire ou manager de la team
    private Long idUser;

    public Team() {}

    public Team(String nameTeam, Long idUser) {
        this.nameTeam = nameTeam;
        this.idUser = idUser;
    }

    public Long getId() { return id; }
    public String getNameTeam() { return nameTeam; }
    public Long getIdUser() { return idUser; }

    public void setNameTeam(String nameTeam) { this.nameTeam = nameTeam; }
    public void setIdUser(Long idUser) { this.idUser = idUser; }
}
