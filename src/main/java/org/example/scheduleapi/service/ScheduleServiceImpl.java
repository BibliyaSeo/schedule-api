package org.example.scheduleapi.service;

import lombok.RequiredArgsConstructor;
import org.example.scheduleapi.dto.ScheduleRequestDto;
import org.example.scheduleapi.dto.ScheduleResponseDto;
import org.example.scheduleapi.entity.Schedule;
import org.example.scheduleapi.repository.ScheduleRepository;
import org.springframework.data.domain.Sort;
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
    public List<ScheduleResponseDto> findSchedulesByAuthor(String author) {
        List<Schedule> schedules;
        if (author == null || author.isEmpty()) {
            schedules = scheduleRepository.findAll(Sort.by(Sort.Direction.DESC, "updatedAt"));
        } else {
            schedules = scheduleRepository.findByAuthorOrderByUpdatedAtDesc(author);
        }
        return schedules.stream().map(ScheduleResponseDto::new).toList();
    }
}
