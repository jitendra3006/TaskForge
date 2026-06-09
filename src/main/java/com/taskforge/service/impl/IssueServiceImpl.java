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
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
public List<IssueResponseDto> getAllIssues() {

    return issueRepository.findAll()
            .stream()
            .map(issue -> IssueResponseDto.builder()
                    .id(issue.getId())
                    .title(issue.getTitle())
                    .description(issue.getDescription())
                    .status(issue.getStatus())
                    .priority(issue.getPriority())
                    .assignedUserId(issue.getAssignedUserId())
                    .createdBy(issue.getCreatedBy())
                    .build())
            .collect(Collectors.toList());
        }

        @Override
        public IssueResponseDto getIssueById(String id) {

            Issue issue = issueRepository.findById(id)
                .orElseThrow(() ->
                    new RuntimeException("Issue not found"));

    return IssueResponseDto.builder()
            .id(issue.getId())
            .title(issue.getTitle())
            .description(issue.getDescription())
            .status(issue.getStatus())
            .priority(issue.getPriority())
            .assignedUserId(issue.getAssignedUserId())
            .createdBy(issue.getCreatedBy())
            .build();
        }


        @Override
public IssueResponseDto updateIssue(
        String id,
        IssueRequestDto request) {

    Issue issue = issueRepository.findById(id)
            .orElseThrow(() ->
                    new RuntimeException("Issue not found"));

    issue.setTitle(request.getTitle());
    issue.setDescription(request.getDescription());
    issue.setPriority(request.getPriority());
    issue.setAssignedUserId(request.getAssignedUserId());

    Issue updatedIssue = issueRepository.save(issue);

    return IssueResponseDto.builder()
            .id(updatedIssue.getId())
            .title(updatedIssue.getTitle())
            .description(updatedIssue.getDescription())
            .status(updatedIssue.getStatus())
            .priority(updatedIssue.getPriority())
            .assignedUserId(updatedIssue.getAssignedUserId())
            .createdBy(updatedIssue.getCreatedBy())
            .build();
}


        @Override
        public void deleteIssue(String id) {

        Issue issue = issueRepository.findById(id)
            .orElseThrow(() ->
                    new RuntimeException("Issue not found"));

        issueRepository.delete(issue);
        }

        @Override
public IssueResponseDto updateIssueStatus(
        String id,
        String status) {

    Issue issue = issueRepository.findById(id)
            .orElseThrow(() ->
                    new RuntimeException("Issue not found"));

    issue.setStatus(IssueStatus.valueOf(status.toUpperCase()));

    Issue updatedIssue = issueRepository.save(issue);

    return IssueResponseDto.builder()
            .id(updatedIssue.getId())
            .title(updatedIssue.getTitle())
            .description(updatedIssue.getDescription())
            .status(updatedIssue.getStatus())
            .priority(updatedIssue.getPriority())
            .assignedUserId(updatedIssue.getAssignedUserId())
            .createdBy(updatedIssue.getCreatedBy())
            .build();
}

@Override
public IssueResponseDto assignIssue(
        String issueId,
        String userId) {

    Issue issue = issueRepository.findById(issueId)
            .orElseThrow(() ->
                    new RuntimeException("Issue not found"));

    User user = userRepository.findById(userId)
            .orElseThrow(() ->
                    new RuntimeException("User not found"));

    issue.setAssignedUserId(user.getId());

    Issue updatedIssue = issueRepository.save(issue);

    return IssueResponseDto.builder()
            .id(updatedIssue.getId())
            .title(updatedIssue.getTitle())
            .description(updatedIssue.getDescription())
            .status(updatedIssue.getStatus())
            .priority(updatedIssue.getPriority())
            .assignedUserId(updatedIssue.getAssignedUserId())
            .createdBy(updatedIssue.getCreatedBy())
            .build();
}
}