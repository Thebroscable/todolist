package com.example.demo.controller;

import com.example.demo.dto.request.TaskCompleteRequest;
import com.example.demo.dto.request.TaskRequest;
import com.example.demo.dto.response.TaskResponse;
import com.example.demo.entity.Task;
import com.example.demo.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

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

    @PutMapping("/task/complete/{taskId}")
    public ResponseEntity<TaskResponse> completeTask(@RequestBody TaskCompleteRequest request,
                                                     @PathVariable Long taskId) {
        return ResponseEntity.ok(taskService.completeTask(request, taskId));
    }

    @DeleteMapping("/task/{taskId}")
    public ResponseEntity<TaskResponse> deleteTask(@PathVariable Long taskId) {
        return ResponseEntity.ok(taskService.deleteTask(taskId));
    }

    @GetMapping("/task/{taskId}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long taskId) {
        return ResponseEntity.ok(taskService.getTaskById(taskId));
    }

    @GetMapping("/upcomingTasks")
    public ResponseEntity<Collection<Task>> getAllUpcomingTasks() {
        return ResponseEntity.ok(taskService.getAllUpcomingTasks());
    }

    @GetMapping("/todayTasks")
    public ResponseEntity<Collection<Task>> getAllTodayTasks() {
        return ResponseEntity.ok(taskService.getAllTodayTasks());
    }

    @GetMapping("/allTasks")
    public ResponseEntity<Collection<Task>> getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @GetMapping("/completedTasks")
    public ResponseEntity<Collection<Task>> getCompletedTasks() {
        return ResponseEntity.ok(taskService.getCompletedTasks());
    }
}
