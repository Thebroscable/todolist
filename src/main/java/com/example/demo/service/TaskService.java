package com.example.demo.service;

import com.example.demo.dto.request.TaskCompleteRequest;
import com.example.demo.dto.request.TaskRequest;
import com.example.demo.dto.response.TaskResponse;
import com.example.demo.entity.Task;
import com.example.demo.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    public TaskResponse createTask(TaskRequest request) {
        Task newTask = Task.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .due_date(request.getDue_date())
                .due_time(request.getDue_time())
                .is_completed(request.getIs_completed())
                .build();

        Task savedTask = taskRepository.save(newTask);
        return new TaskResponse(savedTask, "Task added successfully");
    }

    public TaskResponse updateTask(TaskRequest request, Long taskId) {
        Task task = taskRepository.getReferenceById(taskId);

        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setDue_date(request.getDue_date());
        task.setDue_time(request.getDue_time());
        task.setIs_completed(request.getIs_completed());

        Task savedTask = taskRepository.save(task);
        return new TaskResponse(savedTask, "Task updated successfully");
    }

    public TaskResponse completeTask(TaskCompleteRequest request, Long taskId) {
        Task task = taskRepository.getReferenceById(taskId);
        task.setIs_completed(request.getIs_completed());

        Task savedTask = taskRepository.save(task);
        return new TaskResponse(savedTask, "Task completed successfully");
    }

    public TaskResponse deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
        return new TaskResponse(taskId, "Task deleted successfully");
    }

    public Task getTaskById(Long taskId) {
        Optional<Task> optionalTask = taskRepository.findById(taskId);

        if(optionalTask.isPresent()) {
            return optionalTask.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public Collection<Task> getAllTasks() {
        return taskRepository.findAllTasks();
    }

    public Collection<Task> getCompletedTasks() {
        return taskRepository.findAllCompletedTasks();
    }

    public Collection<Task> getAllTodayTasks() {
        return taskRepository.findAllTodayTasks();
    }

    public Collection<Task> getAllUpcomingTasks() {
        return taskRepository.findAllUpcomingTasks();
    }
}
