package com.example.api;

import com.example.dao.TaskRepository;
import com.example.domain.Task;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/tasks")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TaskResource {

    private final TaskRepository taskRepository = new TaskRepository();

    @POST
    public Response createTask(TaskRequest request) {
        Task task = new Task();
        task.setNameTask(request.nameTask);
        task.setDescription(request.description);
        task.setDate(request.date);


        Task created = taskRepository.save(task, request.idUser, request.idTeam);

        return Response.status(Response.Status.CREATED).entity(created).build();
    }

    @GET
    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    @GET
    @Path("/{id}")
    public Response getTaskById(@PathParam("id") Long id) {
        Task task = taskRepository.findById(id);
        if (task == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(task).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateTask(@PathParam("id") Long id, TaskRequest request) {
        Task task = new Task();
        task.setIdTask(id);
        task.setNameTask(request.nameTask);
        task.setDescription(request.description);
        task.setDate(request.date);

        Task updated = taskRepository.save(task, request.idUser, request.idTeam);
        return Response.ok(updated).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteTask(@PathParam("id") Long id) {
        boolean deleted = taskRepository.delete(id);
        if (deleted) {
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    // Le DTO pour transporter les données JSON
    public static class TaskRequest {
        public String nameTask;
        public String description;
        public String date;
        public Long idUser;
        public Long idTeam;
    }
}