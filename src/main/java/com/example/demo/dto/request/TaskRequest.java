package com.example.demo.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Getter
@Setter
@AllArgsConstructor
public class TaskRequest {

    private String title;

    private String description;

    private String due_date;

    private String due_time;

    private Boolean is_completed;

    public Date getDue_date() {
        if(due_date.isEmpty()) {
            return null;
        }
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date date = dateFormat.parse(due_date);
            return new Date(date.getTime());
        } catch (ParseException e) {
            return null;
        }
    }

    public Time getDue_time() {
        if(due_time.isEmpty()) {
            return null;
        }
        try {
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
            java.util.Date time = timeFormat.parse(due_time);
            return new Time(time.getTime());
        } catch (ParseException e) {
            return null;
        }
    }
}
