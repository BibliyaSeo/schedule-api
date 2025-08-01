package org.example.scheduleapi.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.scheduleapi.entity.Schedule;

@Getter
@NoArgsConstructor
public class ScheduleRequestDto {
    private String title;
    private String contents;
    private String author;
    private String password;

    public ScheduleRequestDto(Schedule schedule) {
        this.title = schedule.getTitle();
        this.contents = schedule.getContents();
        this.author = schedule.getAuthor();
        this.password = schedule.getPassword();
    }
}
