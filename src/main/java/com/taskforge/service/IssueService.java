package com.taskforge.service;

import java.util.List;

import com.taskforge.dto.IssueRequestDto;
import com.taskforge.dto.IssueResponseDto;

public interface IssueService {

    IssueResponseDto createIssue(IssueRequestDto request);

    List<IssueResponseDto> getAllIssues();

    IssueResponseDto getIssueById(String id);

    IssueResponseDto updateIssue(String id, IssueRequestDto request);

    void deleteIssue(String id);
}