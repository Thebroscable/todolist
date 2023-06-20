package com.example.demo.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class TaskCompleteRequest {

    @NotNull(message = "is_completed cannot be null")
    private Boolean is_completed;
}
