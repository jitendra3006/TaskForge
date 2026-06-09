package com.taskforge.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "issues")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Issue {

    @Id
    private String id;

    private String title;

    private String description;

    private IssueStatus status;

    private IssuePriority priority;

    private String assignedUserId;

    private String createdBy;
}