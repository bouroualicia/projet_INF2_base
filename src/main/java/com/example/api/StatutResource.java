package com.example.api;

import com.example.domain.Statut;
import com.example.service.StatutService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/statuts")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StatutResource {

    private final StatutService statutService = new StatutService();


    @POST
    public Response createStatut(Statut statut) {
        return Response.status(Response.Status.CREATED)
                .entity(statutService.createStatut(statut))
                .build();
    }

    @GET
    public List<Statut> getAllStatuts() {
        return statutService.getAllStatuts();
    }

    @GET
    @Path("/{id}")
    public Response getStatutById(@PathParam("id") Long id) {
        Statut statut = statutService.getStatutById(id);
        if (statut == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(statut).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateStatut(@PathParam("id") Long id, Statut statut) {
        Statut updated = statutService.updateStatut(id, statut);
        if (updated == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(updated).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteStatut(@PathParam("id") Long id) {
        if (!statutService.deleteStatut(id)) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.noContent().build();
    }
}
