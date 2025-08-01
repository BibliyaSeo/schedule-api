package org.example.scheduleapi.controller;

import lombok.RequiredArgsConstructor;
import org.example.scheduleapi.dto.CommentRequestDto;
import org.example.scheduleapi.dto.CommentResponseDto;
import org.example.scheduleapi.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedules/{scheduleId}/comments")
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentResponseDto> createComment(@PathVariable Long scheduleId, @RequestBody CommentRequestDto dto) {
        return new ResponseEntity<>(commentService.createComment(scheduleId, dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CommentResponseDto>> getComments(@PathVariable Long scheduleId) {
        return new ResponseEntity<>(commentService.getComments(scheduleId), HttpStatus.OK);
    }
}
