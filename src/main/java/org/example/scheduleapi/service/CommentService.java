package org.example.scheduleapi.service;

import org.example.scheduleapi.dto.CommentRequestDto;
import org.example.scheduleapi.dto.CommentResponseDto;

import java.util.List;

public interface CommentService {
    CommentResponseDto createComment(Long scheduleId, CommentRequestDto dto);

    List<CommentResponseDto> getComments(Long scheduleId);
}
