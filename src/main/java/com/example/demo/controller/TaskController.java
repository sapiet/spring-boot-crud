package com.example.api.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Task;
import com.example.demo.service.TaskService;

@RestController
public class TaskController {
    @Autowired
    private TaskService taskService;

    /**
     * Create - Add a new task
     * @param task An object task
     * @return The task object saved
     */
    @PostMapping("/task")
    public Task createTask(@RequestBody Task task) {
        return taskService.saveTask(task);
    }


    /**
     * Read - Get one task
     * @param id The id of the task
     * @return An Task object full filled
     */
    @GetMapping("/task/{id}")
    public Task getTask(@PathVariable("id") final Long id) {
        Optional<Task> task = taskService.getTask(id);
        if(task.isPresent()) {
            return task.get();
        } else {
            return null;
        }
    }

    /**
     * Read - Get all tasks
     * @return - An Iterable object of Task full filled
     */
    @GetMapping("/tasks")
    public Iterable<Task> getTasks() {
        return taskService.getTasks();
    }

    /**
     * Update - Update an existing task
     * @param id - The id of the task to update
     * @param task - The task object updated
     * @return
     */
    @PutMapping("/task/{id}")
    public Task updateTask(@PathVariable("id") final Long id, @RequestBody Task task) {
        Optional<Task> e = taskService.getTask(id);
        if(e.isPresent()) {
            Task currentTask = e.get();

            String name = task.getName();

            if(name != null) {
                currentTask.setName(name);
            }

            taskService.saveTask(currentTask);

            return currentTask;
        } else {
            return null;
        }
    }


    /**
     * Delete - Delete an task
     * @param id - The id of the task to delete
     */
    @DeleteMapping("/task/{id}")
    public void deleteTask(@PathVariable("id") final Long id) {
        taskService.deleteTask(id);
    }
}
