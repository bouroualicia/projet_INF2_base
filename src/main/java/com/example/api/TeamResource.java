package com.example.api;

import com.example.domain.Team;
import com.example.domain.User;
import com.example.persistence.Jpa;
import com.example.service.TeamService;
import jakarta.persistence.EntityManager;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/teams")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TeamResource {

    private final TeamService teamService = new TeamService();

    @POST
    public Response createTeam(TeamRequest request) {
        EntityManager em = Jpa.getEntityManager();
        try {
            Team team = new Team();
            team.setNameTeam(request.nameTeam);

            //pour chercher l utilisateur qui possède l equipe
            if (request.idUser != null) {
                User owner = em.find(User.class, request.idUser);
                team.setOwner(owner);
            }

            Team created = teamService.createTeam(team);
            return Response.status(Response.Status.CREATED).entity(created).build();
        } finally {
            em.close();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteTeam(@PathParam("id") Long id) {

        boolean deleted = new com.example.dao.TeamRepository().delete(id);

        if (deleted) {
            return Response.status(Response.Status.NO_CONTENT).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    public List<Team> getTeams() {
        return teamService.getAllTeams();
    }


    public static class TeamRequest {
        public String nameTeam;
        public Long idUser;
    }

    @PUT
    @Path("/{id}")
    public Response updateTeam(@PathParam("id") Long id, Team team) {
        team.setId(id);
        Team updated = teamService.updateTeam(team);
        return Response.ok(updated).build();
    }

}