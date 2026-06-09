package com.taskforge.service.impl;

import com.taskforge.dto.IssueRequestDto;
import com.taskforge.dto.IssueResponseDto;
import com.taskforge.entity.Issue;
import com.taskforge.entity.IssueStatus;
import com.taskforge.entity.User;
import com.taskforge.repository.IssueRepository;
import com.taskforge.repository.UserRepository;
import com.taskforge.service.IssueService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IssueServiceImpl implements IssueService {

    private final IssueRepository issueRepository;
    private final UserRepository userRepository;

    @Override
    public IssueResponseDto createIssue(IssueRequestDto request) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User creator = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Issue issue = Issue.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .priority(request.getPriority())
                .status(IssueStatus.TODO)
                .assignedUserId(request.getAssignedUserId())
                .createdBy(creator.getId())
                .build();

        Issue savedIssue = issueRepository.save(issue);

        return IssueResponseDto.builder()
                .id(savedIssue.getId())
                .title(savedIssue.getTitle())
                .description(savedIssue.getDescription())
                .status(savedIssue.getStatus())
                .priority(savedIssue.getPriority())
                .assignedUserId(savedIssue.getAssignedUserId())
                .createdBy(savedIssue.getCreatedBy())
                .build();
    }
}