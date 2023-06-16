package com.example.demo.service;

import com.example.demo.dto.request.TaskRequest;
import com.example.demo.dto.response.TaskResponse;
import com.example.demo.entity.Task;
import com.example.demo.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public Mono<TaskResponse> addTask(TaskRequest request) {
        Task newTask = Task.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .due_date(request.getDue_date())
                .due_time(request.getDue_time())
                .is_completed(request.getIs_completed())
                .build();

        return taskRepository.save(newTask)
                .map(TaskResponse::new);
    }
}
