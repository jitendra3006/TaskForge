package com.taskforge.controller;

import com.taskforge.dto.IssueRequestDto;
import com.taskforge.dto.IssueResponseDto;
import com.taskforge.service.IssueService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<IssueResponseDto>> getAllIssues() {
        return ResponseEntity.ok(
            issueService.getAllIssues()
    );
    }

    @GetMapping("/{id}")
    public ResponseEntity<IssueResponseDto> getIssueById(
        @PathVariable String id) {

    return ResponseEntity.ok(
            issueService.getIssueById(id)
    );
    }


    @PutMapping("/{id}")
    public ResponseEntity<IssueResponseDto> updateIssue(
        @PathVariable String id,
        @Valid @RequestBody IssueRequestDto request) {

    return ResponseEntity.ok(
            issueService.updateIssue(id, request)
        );
    }

    @PatchMapping("/{id}/status")
public ResponseEntity<IssueResponseDto> updateIssueStatus(
        @PathVariable String id,
        @RequestParam String status) {

    return ResponseEntity.ok(
            issueService.updateIssueStatus(id, status)
    );
}

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteIssue(
        @PathVariable String id) {

        issueService.deleteIssue(id);

    return ResponseEntity.ok(
            "Issue deleted successfully"
         );
    }

    @PatchMapping("/{issueId}/assign")
public ResponseEntity<IssueResponseDto> assignIssue(
        @PathVariable String issueId,
        @RequestParam String userId) {

    return ResponseEntity.ok(
            issueService.assignIssue(issueId, userId)
    );
}

}