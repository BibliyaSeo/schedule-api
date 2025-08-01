package org.example.scheduleapi.service;

import lombok.RequiredArgsConstructor;
import org.example.scheduleapi.dto.ScheduleRequestDto;
import org.example.scheduleapi.dto.ScheduleResponseDto;
import org.example.scheduleapi.entity.Schedule;
import org.example.scheduleapi.repository.ScheduleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleRepository scheduleRepository;

    @Override
    @Transactional
    public ScheduleResponseDto save(ScheduleRequestDto dto) {
        Schedule savedSchedule = scheduleRepository.save(new Schedule(dto.getTitle(), dto.getContents(), dto.getAuthor(), dto.getPassword()));
        return new ScheduleResponseDto(savedSchedule);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ScheduleResponseDto> findAllSchedules() {
        List<Schedule> schedules = scheduleRepository.findAll();
        return schedules.stream().map(ScheduleResponseDto::new).toList();
    }
}
