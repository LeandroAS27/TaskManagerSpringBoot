package com.example.TaskManager.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.TaskManager.service.TaskService;

import java.util.List;
import com.example.TaskManager.model.Task;
import com.example.TaskManager.model.UpdateTaskRequest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;



@RestController
public class TaskController {  
    
    private final TaskService TaskService;

    public TaskController(TaskService TaskService) {
        this.TaskService = TaskService;
    }

    @GetMapping("/")
    public String funcionando(){
        return "Funcionando!";
    }
    

    @GetMapping("/tasks")
    public List<Task> getTasks(){
        return TaskService.getTasks();
    }

    @GetMapping("/tasks/{id}")
    public List<Task> getTaskById(@PathVariable Long id){
       if(TaskService.getTaskById(id).isEmpty()){
            return List.of(new Task(-1L, "Task not found", "Task not found"));
       }
       return TaskService.getTaskById(id);
    }

    //faltou criar o post 
    @PostMapping("/tasks")
    public String postMethodName(@RequestBody String entity) {
        //TODO: process POST request
        
        return entity;
    }
    

    @PutMapping("/tasks/{id}")
    public ResponseEntity<String> updateTaskById(@PathVariable Long id, @RequestBody UpdateTaskRequest request) {
        String result = TaskService.updateTaskById(id, request.description());

        if (result.equals("Task not found!")) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(result);
    }
    
    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<String> deleteTaskById(@PathVariable Long id) {
        String result = TaskService.deleteTaskById(id);

        if (result.equals("Task not found!")) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(result);
    }
}
