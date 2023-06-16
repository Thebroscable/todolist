package com.example.demo.dto.request;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.sql.Time;

@Getter
@Setter
@AllArgsConstructor
public class TaskRequest {

    private String title;

    private String description;

    private Date due_date;

    private Time due_time;

    private Boolean is_completed;
}
