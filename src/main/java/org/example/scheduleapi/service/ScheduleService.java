package org.example.scheduleapi.service;

import org.example.scheduleapi.dto.PasswordRequestDto;
import org.example.scheduleapi.dto.ScheduleRequestDto;
import org.example.scheduleapi.dto.ScheduleResponseDto;
import org.example.scheduleapi.dto.ScheduleUpdateRequestDto;
import org.example.scheduleapi.entity.Schedule;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ScheduleService {
    ScheduleResponseDto save(@RequestBody ScheduleRequestDto dto);

    List<ScheduleResponseDto> findSchedulesByWriter(String writer);

    ScheduleResponseDto findScheduleById(Long id);

    Schedule findScheduleOrThrow(Long id);

    ScheduleResponseDto updateSchedule(Long id, @RequestBody ScheduleUpdateRequestDto dto);

    void deleteSchedule(Long id, @RequestBody PasswordRequestDto dto);

    Schedule validateWithPassword(Long id, String password);
}
