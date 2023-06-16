package com.example.demo.dto.response;

import com.example.demo.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.sql.Time;

@Getter
@AllArgsConstructor
public class TaskResponse {

    private Long id;
    private String message;

    public TaskResponse(Task task, String m) {
        id = task.getId();
        message = m;
    }
}
