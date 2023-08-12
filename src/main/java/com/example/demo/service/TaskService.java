package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Task;
import com.example.demo.repository.TaskRepository;

import lombok.Data;

@Data
@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public Optional<Task> getTask(final Long id) {
        return taskRepository.findById(id);
    }

    public Iterable<Task> getTasks() {
        return taskRepository.findAll();
    }

    public void deleteTask(final Long id) {
        taskRepository.deleteById(id);
    }

    public Task saveTask(Task task) {
        Task savedTask = taskRepository.save(task);
        return savedTask;
    }
}