package com.example.domain;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "teams")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nameTeam;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id") 
    private User owner;

    public Team() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNameTeam() { return nameTeam; }
    public void setNameTeam(String nameTeam) { this.nameTeam = nameTeam; }

    public User getOwner() { return owner; }
    public void setOwner(User owner) { this.owner = owner; }
}