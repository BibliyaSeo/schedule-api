package org.example.scheduleapi.controller;

import lombok.RequiredArgsConstructor;
import org.example.scheduleapi.dto.PasswordRequestDto;
import org.example.scheduleapi.dto.ScheduleRequestDto;
import org.example.scheduleapi.dto.ScheduleResponseDto;
import org.example.scheduleapi.dto.ScheduleUpdateRequestDto;
import org.example.scheduleapi.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedules")
public class ScheduleController {
    private final ScheduleService scheduleService;

    // 일정 생성
    @PostMapping
    public ResponseEntity<ScheduleResponseDto> createSchedule(@RequestBody ScheduleRequestDto dto) {
        return new ResponseEntity<>(scheduleService.save(dto), HttpStatus.CREATED);
    }

    // 일정 조회
    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> getSchedules(@RequestParam(required = false) String writer) {
        return new ResponseEntity<>(scheduleService.findSchedulesByWriter(writer), HttpStatus.OK);
    }

    // 일정 단건 조회
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> getSchedule(@PathVariable Long id) {
        return new ResponseEntity<>(scheduleService.findScheduleById(id), HttpStatus.OK);
    }

    // 일정 수정
    @PatchMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(@PathVariable Long id, @RequestBody ScheduleUpdateRequestDto dto) {
        return new ResponseEntity<>(scheduleService.updateSchedule(id, dto), HttpStatus.OK);
    }

    // 일정 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id, @RequestBody PasswordRequestDto dto) {
        scheduleService.deleteSchedule(id, dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

