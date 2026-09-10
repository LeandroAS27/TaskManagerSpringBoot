package com.example.TaskManager.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.example.TaskManager.model.Task;
import java.util.ArrayList;

@Service 
public class TaskService {
    List<Task> tasks = new ArrayList<>(List.of(
    new Task(1L, "Task 1", "Description 1"), 
    new Task(2L, "Task 2", "Description 2"), 
    new Task(3L, "Task 3", "Description 3")
    ));
    public List<Task> getTasks() {
        return tasks;
    }

    public Task addTask(String title, String description) {
        long nextId = tasks.stream()
                .mapToLong(task -> task.getId())
                .max()
                .orElse(0L) + 1;
        if(title == null || title.isEmpty() || description == null || description.isEmpty()){
            throw new IllegalArgumentException("There's a empty field in the request body. Please fill all fields and try again.");
        }
        Task task = new Task(nextId, title, description);
        tasks.add(task);
        return task;
    }

    public List<Task> getTaskById(long id){
        return tasks.stream().filter(task -> task.getId() == id).toList();
    }

    public String updateTaskById(long id, String description){
        return tasks.stream().filter(task -> task.getId() == id).findFirst().map(task -> {
            task.setDescription(description);
            return "Task updated successfully!";
        }).orElse("Task not found!");
    }

    public String deleteTaskById(long id){
        return tasks.stream().filter(task -> task.getId() == id).findFirst().map(task -> {
            tasks.remove(task);
            return "Task deleted successfully!";
        }).orElse("Task not found!");
    }

}
