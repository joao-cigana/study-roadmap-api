package dev.cigana.coursesapi.services;

import dev.cigana.coursesapi.domain.Course;
import dev.cigana.coursesapi.domain.Platform;
import dev.cigana.coursesapi.domain.Topic;
import dev.cigana.coursesapi.domain.dtos.CourseFormDTO;
import dev.cigana.coursesapi.domain.exceptions.ResourceNotFoundException;
import dev.cigana.coursesapi.repositories.CourseRepository;
import dev.cigana.coursesapi.repositories.PlatformRepository;
import dev.cigana.coursesapi.repositories.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CourseService {

    private static final String COURSE_NOT_FOUND = "Course not found - id: ";
    private static final String PLATFORM_NOT_FOUND = "Platform not found - id: ";
    private static final String TOPIC_NOT_FOUND = "Topic not found - id: ";

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private PlatformRepository platformRepository;

    @Autowired
    private TopicRepository topicRepository;

    public Course findById(UUID id){
        return courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(COURSE_NOT_FOUND + id));
    }

    public Page<Course> findAll(Pageable pageable){
        return courseRepository.findAll(pageable);
    }

    public Page<Course> findByPlatform(UUID platformId, Pageable pageable){
        if(!platformRepository.existsById(platformId)){
                throw new ResourceNotFoundException(PLATFORM_NOT_FOUND + platformId);
        }
        return courseRepository.findByPlatformId(platformId, pageable);
    }

    public Page<Course> findByTopic(UUID topicId, Pageable pageable){
        if (!topicRepository.existsById(topicId)){
            throw new ResourceNotFoundException(TOPIC_NOT_FOUND + topicId);
        }
        return courseRepository.findByTopicsId(topicId, pageable);
    }

    public Course create(CourseFormDTO dto){
        if(!platformRepository.existsById(dto.getPlatform().getId())){
            throw new ResourceNotFoundException(PLATFORM_NOT_FOUND + dto.getPlatform().getId());
        }
        for(Topic topic : dto.getTopics()){
            if(!topicRepository.existsById(topic.getId())){
                throw new ResourceNotFoundException(TOPIC_NOT_FOUND + topic.getId());
            }
        }
        Course course = new Course(dto);
        return courseRepository.save(course);
    }

    public void update(Course course){
        Course updatedCourse = courseRepository.findById(course.getId())
                .orElseThrow(() -> new ResourceNotFoundException(COURSE_NOT_FOUND + course.getId()));
        updatedCourse.setConclusionDate(course.getConclusionDate());
        updatedCourse.setCertificateLink(course.getCertificateLink());
        courseRepository.save(updatedCourse);
    }

    public void delete (UUID id){
        if(!courseRepository.existsById(id)){
            throw new ResourceNotFoundException(COURSE_NOT_FOUND + id);
        }
        courseRepository.deleteById(id);
    }

}
