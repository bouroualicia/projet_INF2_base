package com.example.service;

import com.example.dao.TaskRepository;
import com.example.domain.Task;
import java.util.List;

public class TaskService {

    private final TaskRepository taskRepository = new TaskRepository();

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    public void deleteTask(Long id) {
        taskRepository.delete(id);
    }
}