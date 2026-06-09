package com.taskforge.dto;

import com.taskforge.entity.IssuePriority;
import com.taskforge.entity.IssueStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IssueResponseDto {

    private String id;

    private String title;

    private String description;

    private IssueStatus status;

    private IssuePriority priority;

    private String assignedUserId;

    private String createdBy;
}