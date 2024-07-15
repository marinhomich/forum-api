package dev.marinhomich.forum_api.service;

import dev.marinhomich.forum_api.configuration.DateAndTimeConfigurations;
import dev.marinhomich.forum_api.controller.TopicController;
import dev.marinhomich.forum_api.dto.TopicInputDto;
import dev.marinhomich.forum_api.entity.Topic;
import dev.marinhomich.forum_api.entity.User;
import dev.marinhomich.forum_api.repository.CourseRepository;
import dev.marinhomich.forum_api.repository.TopicRepository;
import dev.marinhomich.forum_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service for {@link TopicController}
 */
@Service
public class TopicService {
    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public Topic create(TopicInputDto dto) {
        var topic = new Topic();
        var course = courseRepository.findFirstByName(dto.course().name());
        var authenticatedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var user = userRepository.findById(authenticatedUser.getId());
        var now = DateAndTimeConfigurations.generateDateForNow();

        topic.setStatus(true);
        topic.setRegisterDate(now);
        topic.setLastUpdate(now);
        topic.setTitle(dto.title());
        topic.setMessage(dto.message());
        course.ifPresent(topic::setCourse);
        user.ifPresent(topic::setUser);

        course.ifPresent(c -> c.addTopic(topic));
        user.ifPresent(u -> u.addTopic(topic));

        topicRepository.save(topic);

        return topic;
    }

    public Page<Topic> list(Pageable pageable) {
        return topicRepository.findAll(pageable);
    }

    @Transactional
    public Topic update(Long id, TopicInputDto dto) {
        Topic topic = null;

        if (isUserAuthorized(id)) {
            topic = topicRepository.findById(id).orElseThrow();
            var now = DateAndTimeConfigurations.generateDateForNow();
            topic.setLastUpdate(now);

            if (dto.title() != null && !dto.title().isBlank()) {
                topic.setTitle(dto.title());
            }

            if (dto.message() != null && !dto.message().isBlank()) {
                topic.setMessage(dto.message());
            }

            if (
                    dto.course() != null &&
                    dto.course().name() != null &&
                    !dto.course().name().isBlank()
            ) {
                var course = courseRepository.findFirstByName(dto.course().name());
                course.ifPresent(topic::setCourse);
            }

            topicRepository.save(topic);
        }

        return topic;
    }

    @Transactional
    public boolean delete(Long id) {

        if (isUserAuthorized(id)) {
            topicRepository.deleteById(id);
            return true;
        }

        return false;
    }

    public Topic detail(Long id) {
        return topicRepository.findById(id).orElseThrow();
    }

    private boolean isUserAuthorized(Long id) {
        var topic = topicRepository.findById(id).orElseThrow();
        var authenticatedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return topicRepository.existsByIdAndUserId(topic.getId(), authenticatedUser.getId());
    }
}
