package com.example.demo.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskCompleteRequest {

    @NotNull(message = "is_completed cannot be null")
    private Boolean is_completed;
}
