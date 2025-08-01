package org.example.scheduleapi.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ScheduleUpdateRequestDto {
    private String title;
    private String writer;
    private String password;
}
