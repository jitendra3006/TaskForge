package com.taskforge.dto;

import com.taskforge.entity.IssuePriority;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class IssueRequestDto {

    @NotBlank(message = "Title is required")
    private String title;

    private String description;

    private IssuePriority priority;

    private String assignedUserId;
}