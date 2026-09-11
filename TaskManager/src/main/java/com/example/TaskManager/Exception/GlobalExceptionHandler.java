package com.example.TaskManager.Exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.ResponseEntity;

@RestControllerAdvice 
public class GlobalExceptionHandler {
    
    @ExceptionHandler(TaskNotFoundException.class)
        public ResponseEntity<String> handleTaskNotFound(TaskNotFoundException ex){
            return ResponseEntity.status(404).body(ex.getMessage());
        }
    }
