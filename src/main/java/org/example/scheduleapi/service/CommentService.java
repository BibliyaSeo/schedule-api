package org.example.scheduleapi.service;

import org.example.scheduleapi.dto.CommentRequestDto;
import org.example.scheduleapi.dto.CommentResponseDto;
import org.example.scheduleapi.dto.PasswordRequestDto;
import org.example.scheduleapi.entity.Comment;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface CommentService {
    CommentResponseDto createComment(Long scheduleId, CommentRequestDto dto);

    List<CommentResponseDto> getComments(Long scheduleId);

    Comment findCommentOrThrow(Long id);

    CommentResponseDto updateComment(Long id, CommentRequestDto dto);

    void deleteComment(Long id, @RequestBody PasswordRequestDto dto);

    Comment validateWithPassword(Long id, String password);
}
