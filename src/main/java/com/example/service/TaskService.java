package com.example.service;

import com.example.dao.TaskRepository;
import com.example.domain.Task;
import java.util.List;

public class TaskService {

    private final TaskRepository taskRepository = new TaskRepository();


    public Task createTask(Task task, Long userId, Long teamId) {
        return taskRepository.save(task, userId, teamId);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id);
    }
}