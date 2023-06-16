package com.example.demo.controller;

import com.example.demo.dto.request.TaskRequest;
import com.example.demo.dto.response.TaskResponse;
import com.example.demo.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping("/task")
    public ResponseEntity<TaskResponse> createTask(@RequestBody TaskRequest request) {
        return ResponseEntity.ok(taskService.createTask(request));
    }

    @PutMapping("/task/{taskId}")
    public ResponseEntity<TaskResponse> updateTask(@RequestBody TaskRequest request,
                                                   @PathVariable Long taskId) {
        return ResponseEntity.ok(taskService.updateTask(request, taskId));
    }

    @DeleteMapping("/task/{taskId}")
    public ResponseEntity<TaskResponse> deleteTask(@PathVariable Long taskId) {
        return ResponseEntity.ok(taskService.deleteTask(taskId));
    }
}
