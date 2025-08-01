package org.example.scheduleapi.service;

import lombok.RequiredArgsConstructor;
import org.example.scheduleapi.dto.CommentRequestDto;
import org.example.scheduleapi.dto.CommentResponseDto;
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
}
