package dev.marinhomich.forum_api.repository;

import dev.marinhomich.forum_api.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Long> {
    Boolean existsByIdAndUserId(Long id, Long userId);
}