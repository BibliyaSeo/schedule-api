package org.example.scheduleapi.repository;

import org.example.scheduleapi.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByScheduleIdOrderByCreatedAtDesc(Long scheduleId);

    Long countByScheduleId(Long scheduleId);
}
