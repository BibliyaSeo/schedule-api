package org.example.scheduleapi.service;

import lombok.RequiredArgsConstructor;
import org.example.scheduleapi.dto.*;
import org.example.scheduleapi.entity.Schedule;
import org.example.scheduleapi.repository.ScheduleRepository;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleRepository scheduleRepository;

    @Override
    public ScheduleResponseDto save(ScheduleRequestDto dto) {
        Schedule savedSchedule = scheduleRepository.save(new Schedule(dto.getTitle(), dto.getContents(), dto.getWriter(), dto.getPassword()));
        return new ScheduleResponseDto(savedSchedule);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ScheduleResponseDto> findSchedulesByWriter(String writer) {
        List<Schedule> schedules;
        if (writer == null || writer.isEmpty()) {
            schedules = scheduleRepository.findAll(Sort.by(Sort.Direction.DESC, "updatedAt"));
        } else {
            schedules = scheduleRepository.findByWriterOrderByUpdatedAtDesc(writer);
        }
        return schedules.stream().map(ScheduleResponseDto::new).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ScheduleDetailResponseDto findScheduleById(Long id) {
        Schedule schedule = findScheduleOrThrow(id);
        return getScheduleDetailResponseDto(schedule);
    }

    private static ScheduleDetailResponseDto getScheduleDetailResponseDto(Schedule schedule) {
        return new ScheduleDetailResponseDto(schedule);
    }


    @Override
    public Schedule findScheduleOrThrow(Long id) {
        return scheduleRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 일정입니다."));
    }


    @Override
    public ScheduleResponseDto updateSchedule(Long id, ScheduleUpdateRequestDto dto) {
        Schedule schedule = validateWithPassword(id, dto.getPassword());
        schedule.updateSchedule(dto.getTitle(), dto.getWriter());
        return new ScheduleResponseDto(schedule);
    }

    @Override
    public void deleteSchedule(Long id, PasswordRequestDto dto) {
        Schedule schedule = validateWithPassword(id, dto.getPassword());
        scheduleRepository.delete(schedule);
    }

    @Override
    public Schedule validateWithPassword(Long id, String password) {
        Schedule schedule = findScheduleOrThrow(id);
        if (!schedule.getPassword().equals(password)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }

        return schedule;
    }
}
