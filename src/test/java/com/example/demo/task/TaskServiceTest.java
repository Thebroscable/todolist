package com.example.demo.task;

import com.example.demo.dto.request.TaskCompleteRequest;
import com.example.demo.dto.request.TaskRequest;
import com.example.demo.dto.response.TaskResponse;
import com.example.demo.entity.Task;
import com.example.demo.repository.TaskRepository;
import com.example.demo.service.TaskService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.sql.Time;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @Mock
    private TaskRepository repository;
    private TaskService underTest;

    @BeforeEach
    void setUp() {
        underTest = new TaskService(repository);
    }

    @Test
    void createTask_PassValueToRepositorySave() {
        TaskRequest taskRequest = TaskRequest.builder()
                .title("random task")
                .description("this is a description")
                .due_date("2023-06-20")
                .due_time("20:00")
                .is_completed(false)
                .build();

        when(repository.save(any())).thenReturn(new Task());
        underTest.createTask(taskRequest);

        ArgumentCaptor<Task> argumentCaptor =
                ArgumentCaptor.forClass(Task.class);
        verify(repository).save(argumentCaptor.capture());

        Task capturedTask = argumentCaptor.getValue();

        Assertions.assertThat(capturedTask.getTitle())
                .isEqualTo("random task");
        Assertions.assertThat(capturedTask.getDescription())
                .isEqualTo("this is a description");
        Assertions.assertThat(capturedTask.getDue_date())
                .isEqualTo(Date.valueOf("2023-06-20"));
        Assertions.assertThat(capturedTask.getDue_time())
                .isEqualTo(Time.valueOf("20:00:00"));
        Assertions.assertThat(capturedTask.getIs_completed())
                .isEqualTo(false);
    }

    @Test
    void createTask_ReturnValueFromRepositorySave() {
        Task task = Task.builder()
                .id(10L)
                .title("random task")
                .description("this is a description")
                .due_date(Date.valueOf("2023-06-20"))
                .due_time(Time.valueOf("20:00:00"))
                .is_completed(false)
                .build();
        TaskResponse baseResponse = new TaskResponse(
                task.getId(),
                "Task added successfully"
        );

        when(repository.save(any())).thenReturn(task);
        TaskResponse response = underTest.createTask(new TaskRequest());

        Assertions.assertThat(baseResponse)
                .usingRecursiveComparison()
                .isEqualTo(response);
    }

    @Test
    void updateTask_PassValueToRepositoryGetReferenceById() {
        when(repository.getReferenceById(any())).thenReturn(new Task());
        when(repository.save(any())).thenReturn(new Task());
        underTest.updateTask(new TaskRequest(),10L);

        ArgumentCaptor<Long> argumentCaptor =
                ArgumentCaptor.forClass(Long.class);
        verify(repository).getReferenceById(argumentCaptor.capture());
        Long capturedValue = argumentCaptor.getValue();

        Assertions.assertThat(capturedValue).isEqualTo(10L);
    }

    @Test
    void updateTask_PassValueToRepositorySave() {
        TaskRequest taskRequest = TaskRequest.builder()
                .title("new random task")
                .description("this is a new description")
                .due_date("2023-06-21")
                .due_time("19:00")
                .is_completed(true)
                .build();
        Task task = Task.builder()
                .id(10L)
                .title("random task")
                .description("this is a description")
                .due_date(Date.valueOf("2023-06-20"))
                .due_time(Time.valueOf("20:00:00"))
                .is_completed(false)
                .build();
        when(repository.getReferenceById(any())).thenReturn(task);
        when(repository.save(any())).thenReturn(new Task());

        underTest.updateTask(taskRequest, 10L);

        ArgumentCaptor<Task> argumentCaptor =
                ArgumentCaptor.forClass(Task.class);
        verify(repository).save(argumentCaptor.capture());

        Task capturedTask = argumentCaptor.getValue();

        Task updatedTask = Task.builder()
                .id(10L)
                .title("new random task")
                .description("this is a new description")
                .due_date(Date.valueOf("2023-06-21"))
                .due_time(Time.valueOf("19:00:00"))
                .is_completed(true)
                .build();
        Assertions.assertThat(capturedTask)
                .usingRecursiveComparison().isEqualTo(updatedTask);
    }

    @Test
    void updateTask_ReturnValueFromRepositorySave() {
        Task task = Task.builder()
                .id(10L)
                .title("random task")
                .description("this is a description")
                .due_date(Date.valueOf("2023-06-20"))
                .due_time(Time.valueOf("20:00:00"))
                .is_completed(false)
                .build();
        when(repository.getReferenceById(any())).thenReturn(new Task());
        when(repository.save(any())).thenReturn(task);

        TaskResponse response =
                underTest.updateTask(new TaskRequest(), 10L);

        TaskResponse baseResponse = new TaskResponse(
            task.getId(),
            "Task updated successfully"
        );

        Assertions.assertThat(baseResponse)
                .usingRecursiveComparison().isEqualTo(response);
    }

    @Test
    void completeTask_PassValueToRepositoryGetReferenceById() {
        when(repository.getReferenceById(any())).thenReturn(new Task());
        when(repository.save(any())).thenReturn(new Task());
        underTest.completeTask(new TaskCompleteRequest(),10L);

        ArgumentCaptor<Long> argumentCaptor =
                ArgumentCaptor.forClass(Long.class);
        verify(repository).getReferenceById(argumentCaptor.capture());
        Long capturedValue = argumentCaptor.getValue();

        Assertions.assertThat(capturedValue).isEqualTo(10L);
    }

    @Test
    void completeTask_PassValueToRepositorySave() {
        TaskCompleteRequest taskCompleteRequest = TaskCompleteRequest.builder()
                .is_completed(true)
                .build();
        Task task = Task.builder()
                .id(10L)
                .title("random task")
                .description("this is a description")
                .due_date(Date.valueOf("2023-06-20"))
                .due_time(Time.valueOf("20:00:00"))
                .is_completed(false)
                .build();
        when(repository.getReferenceById(any())).thenReturn(task);
        when(repository.save(any())).thenReturn(new Task());

        underTest.completeTask(taskCompleteRequest, 10L);

        ArgumentCaptor<Task> argumentCaptor =
                ArgumentCaptor.forClass(Task.class);
        verify(repository).save(argumentCaptor.capture());

        Task capturedTask = argumentCaptor.getValue();

        Assertions.assertThat(capturedTask.getIs_completed())
                .isEqualTo(true);
    }

    @Test
    void completeTask_ReturnValueFromRepositorySave() {
        Task task = Task.builder()
                .id(10L)
                .title("random task")
                .description("this is a description")
                .due_date(Date.valueOf("2023-06-20"))
                .due_time(Time.valueOf("20:00:00"))
                .is_completed(false)
                .build();
        when(repository.getReferenceById(any())).thenReturn(new Task());
        when(repository.save(any())).thenReturn(task);

        TaskResponse response =
                underTest.completeTask(new TaskCompleteRequest(), 10L);

        TaskResponse baseResponse = new TaskResponse(
                task.getId(),
                "Task completed successfully"
        );

        Assertions.assertThat(baseResponse)
                .usingRecursiveComparison().isEqualTo(response);
    }
}
