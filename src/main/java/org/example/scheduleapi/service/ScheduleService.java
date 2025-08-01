package org.example.scheduleapi.service;

import org.example.scheduleapi.dto.ScheduleRequestDto;
import org.example.scheduleapi.dto.ScheduleResponseDto;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ScheduleService {
    ScheduleResponseDto save(@RequestBody ScheduleRequestDto dto);

    List<ScheduleResponseDto> findSchedulesByAuthor(String author);
}
