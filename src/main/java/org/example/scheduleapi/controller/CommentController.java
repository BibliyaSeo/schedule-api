package org.example.scheduleapi.controller;

import lombok.RequiredArgsConstructor;
import org.example.scheduleapi.dto.CommentRequestDto;
import org.example.scheduleapi.dto.CommentResponseDto;
import org.example.scheduleapi.dto.PasswordRequestDto;
import org.example.scheduleapi.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/schedules/{scheduleId}/comments")
    public ResponseEntity<CommentResponseDto> createComment(@PathVariable Long scheduleId, @RequestBody CommentRequestDto dto) {
        return new ResponseEntity<>(commentService.createComment(scheduleId, dto), HttpStatus.CREATED);
    }

    @GetMapping("/schedules/{scheduleId}/comments")
    public ResponseEntity<List<CommentResponseDto>> getComments(@PathVariable Long scheduleId) {
        return new ResponseEntity<>(commentService.getComments(scheduleId), HttpStatus.OK);
    }

    @PatchMapping("/comments/{id}")
    public ResponseEntity<CommentResponseDto> updateComment(@PathVariable Long id, @RequestBody CommentRequestDto dto) {
        return new ResponseEntity<>(commentService.updateComment(id, dto), HttpStatus.OK);
    }

    @DeleteMapping("/comments/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id, @RequestBody PasswordRequestDto dto) {
        commentService.deleteComment(id, dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
