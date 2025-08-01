package org.example.scheduleapi.service;

import lombok.RequiredArgsConstructor;
import org.example.scheduleapi.dto.CommentRequestDto;
import org.example.scheduleapi.dto.CommentResponseDto;
import org.example.scheduleapi.dto.PasswordRequestDto;
import org.example.scheduleapi.entity.Comment;
import org.example.scheduleapi.entity.Schedule;
import org.example.scheduleapi.repository.CommentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentServiceImpl implements CommentService {
    private final ScheduleService scheduleService;
    private final CommentRepository commentRepository;

    @Override
    public CommentResponseDto createComment(Long scheduleId, CommentRequestDto dto) {
        // 일정 찾기
        Schedule schedule = scheduleService.findScheduleOrThrow(scheduleId);
        // 댓글 제한 (10개)
        if (commentRepository.countByScheduleId(scheduleId) >= 10) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "댓글은 최대 10개까지 작성할 수 있습니다.");
        }
        // 댓글 저장
        Comment comment = new Comment(dto.getContents(), dto.getWriter(), dto.getPassword(), schedule);
        commentRepository.save(comment);
        return new CommentResponseDto(comment);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommentResponseDto> getComments(Long scheduleId) {
        // 일정 찾기
        scheduleService.findScheduleOrThrow(scheduleId);
        return commentRepository.findByScheduleIdOrderByCreatedAtDesc(scheduleId).stream().map(CommentResponseDto::new).toList();
    }

    @Override
    public Comment findCommentOrThrow(Long id) {
        return commentRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 댓글입니다."));
    }

    @Override
    public CommentResponseDto updateComment(Long id, CommentRequestDto dto) {
        Comment comment = validateWithPassword(id, dto.getPassword());
        comment.updateComment(dto.getContents(), dto.getWriter());
        return new CommentResponseDto(comment);
    }

    @Override
    public void deleteComment(Long id, PasswordRequestDto dto) {
        Comment comment = validateWithPassword(id, dto.getPassword());
        commentRepository.delete(comment);
    }

    @Override
    public Comment validateWithPassword(Long id, String password) {
        Comment comment = findCommentOrThrow(id);
        if (!comment.getPassword().equals(password)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }
        return comment;
    }
}
