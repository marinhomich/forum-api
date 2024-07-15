package dev.marinhomich.forum_api.repository;

import dev.marinhomich.forum_api.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {
    Optional<Course> findFirstByName(String name);
}