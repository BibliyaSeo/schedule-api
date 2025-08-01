package org.example.scheduleapi.dto;

import lombok.Getter;
import org.example.scheduleapi.entity.Schedule;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ScheduleDetailResponseDto {
    private final Long id;
    private final String title;
    private final String contents;
    private final String writer;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final List<CommentResponseDto> comments;

    public ScheduleDetailResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.title = schedule.getTitle();
        this.contents = schedule.getContents();
        this.writer = schedule.getWriter();
        this.createdAt = schedule.getCreatedAt();
        this.updatedAt = schedule.getUpdatedAt();

        this.comments = schedule.getComments().stream()
                .map(CommentResponseDto::new)
                .toList();
    }
}
