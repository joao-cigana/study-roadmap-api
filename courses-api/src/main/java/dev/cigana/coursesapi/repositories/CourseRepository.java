package dev.cigana.coursesapi.repositories;

import dev.cigana.coursesapi.domain.Course;
import dev.cigana.coursesapi.domain.Platform;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CourseRepository extends JpaRepository<Course, UUID> {

    Page<Course> findByPlatformId(UUID platformId, Pageable pageable);

    Page<Course> findByTopicsId(UUID topicId, Pageable pageable);

}
