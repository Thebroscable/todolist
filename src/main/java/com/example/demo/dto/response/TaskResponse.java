package com.example.demo.dto.response;

import com.example.demo.entity.Task;
import lombok.Getter;

import java.sql.Date;
import java.sql.Time;

@Getter
public class TaskResponse {

    private Integer id;
    private String title;
    private String description;
    private Date due_date;
    private Time due_time;
    private Boolean is_completed;

    public TaskResponse(Task task) {
        id = task.getId();
        title = task.getTitle();
        description = task.getDescription();
        due_date = task.getDue_date();
        due_time = task.getDue_time();
        is_completed = task.getIs_completed();
    }
}
