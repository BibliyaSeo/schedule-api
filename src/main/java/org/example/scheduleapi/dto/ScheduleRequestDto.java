package org.example.scheduleapi.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.scheduleapi.entity.Schedule;

@Getter
@NoArgsConstructor
public class ScheduleRequestDto {
    private String title;
    private String contents;
    private String writer;
    private String password;

    public ScheduleRequestDto(Schedule schedule) {
        this.title = schedule.getTitle();
        this.contents = schedule.getContents();
        this.writer = schedule.getWriter();
        this.password = schedule.getPassword();
    }
}
