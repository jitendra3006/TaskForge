package com.taskforge.controller;

import com.taskforge.dto.IssueRequestDto;
import com.taskforge.dto.IssueResponseDto;
import com.taskforge.service.IssueService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/issues")
@RequiredArgsConstructor
public class IssueController {

    private final IssueService issueService;

    @PostMapping
    public ResponseEntity<IssueResponseDto> createIssue(
            @Valid @RequestBody IssueRequestDto request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(issueService.createIssue(request));
    }
}