package com.example.service;

import com.example.dao.TaskRepository;
import com.example.domain.Task;
import com.example.messaging.AuditProducer; 
import java.util.List;

public class TaskService {

    private final TaskRepository taskRepository = new TaskRepository();
    private final AuditProducer auditProducer = new AuditProducer();

    public Task createTask(Task task, Long userId, Long teamId) {
        Task savedTask = taskRepository.save(task, userId, teamId);
        if (savedTask != null) {
            auditProducer.sendAuditMessage(
                "TASK_CREATED", 
                "Task", 
                "Title: " + savedTask.getNameTask() + " (User: " + userId + ")"
            );
        }

        return savedTask;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id);
    }
}