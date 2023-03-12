package dev.cigana.coursesapi.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import dev.cigana.coursesapi.domain.Course;
import dev.cigana.coursesapi.domain.dtos.CourseFormDTO;
import dev.cigana.coursesapi.services.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping
    public ResponseEntity<Page<Course>> findAll(Pageable pageable){
        return ResponseEntity.ok(courseService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> findById(@PathVariable UUID id){
        return ResponseEntity.ok(courseService.findById(id));
    }

    @GetMapping("/topic")
    public ResponseEntity<Page<Course>> findAllByTopic(@RequestParam(required = true) UUID topicId, Pageable pageable){
        return ResponseEntity.ok(courseService.findByTopic(topicId, pageable));
    }

    @GetMapping("/platform")
    public ResponseEntity<Page<Course>> findAllByPlatform(@RequestParam(required = true) UUID platformId, Pageable pageable){
        return ResponseEntity.ok(courseService.findByPlatform(platformId, pageable));
    }

    @PostMapping
    public ResponseEntity<Course> create(@RequestBody @Valid CourseFormDTO dto){
        Course newCourse = courseService.create(dto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newCourse.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping
    public ResponseEntity<Course> update(@RequestBody @Valid Course Course){
        courseService.update(Course);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Course> deleteById(@PathVariable UUID id){
        courseService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
