package dev.marinhomich.forum_api.repository;

import dev.marinhomich.forum_api.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Profile findByName(String user);
}