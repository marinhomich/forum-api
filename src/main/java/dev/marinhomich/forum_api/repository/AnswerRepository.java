package dev.marinhomich.forum_api.repository;

import dev.marinhomich.forum_api.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    Boolean existsByIdAndUserId(Long id, Long userId);
}