package org.example.scheduleapi.repository;

import org.example.scheduleapi.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByWriterOrderByUpdatedAtDesc(String writer);
}
