package com.example.api;

import com.example.domain.Task;
import com.example.service.TaskService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/tasks")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TaskResource {

    private final TaskService taskService = new TaskService();

    /**
     * CREATE : Créer une nouvelle tâche
     * POST /api/tasks
     */
    @POST
    public Response createTask(Task task) {
        Task created = taskService.createTask(task);
        return Response.status(Response.Status.CREATED).entity(created).build();
    }

    /**
     * READ : Récupérer toutes les tâches
     * GET /api/tasks
     */
    @GET
    public List<Task> getTasks() {
        return taskService.getAllTasks();
    }

    /**
     * READ : Récupérer une tâche spécifique par son ID
     * GET /api/tasks/{id}
     */
    @GET
    @Path("/{id}")
    public Response getTaskById(@PathParam("id") Long id) {
        Task task = taskService.getTaskById(id);
        if (task == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(task).build();
    }

    /**
     * UPDATE : Modifier une tâche existante
     * PUT /api/tasks/{id}
     */
    @PUT
    @Path("/{id}")
    public Response updateTask(@PathParam("id") Long id, Task task) {
        // On s'assure que l'ID de l'objet correspond à l'ID de l'URL
        task.setIdTask(id);
        Task updated = taskService.createTask(task); // Le repo utilisera em.merge()
        return Response.ok(updated).build();
    }

    /**
     * DELETE : Supprimer une tâche
     * DELETE /api/tasks/{id}
     */
    @DELETE
    @Path("/{id}")
    public Response deleteTask(@PathParam("id") Long id) {
        taskService.deleteTask(id);
        return Response.noContent().build();
    }
}