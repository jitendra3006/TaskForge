package com.taskforge.service;

import com.taskforge.dto.IssueRequestDto;
import com.taskforge.dto.IssueResponseDto;

public interface IssueService {

    IssueResponseDto createIssue(IssueRequestDto request);
}