package dev.cigana.coursesapi.controllers;

import dev.cigana.coursesapi.domain.Topic;
import dev.cigana.coursesapi.domain.dtos.TopicFormDTO;
import dev.cigana.coursesapi.services.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/topics")
public class TopicController {

    @Autowired
    private TopicService topicService;

    @GetMapping
    public ResponseEntity<Page<Topic>> findAll(Pageable pageable){
        return ResponseEntity.ok(topicService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Topic> findById(@PathVariable UUID id){
        return ResponseEntity.ok(topicService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Topic> create(@RequestBody TopicFormDTO dto){
        Topic newTopic = topicService.create(dto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newTopic.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping
    public ResponseEntity<Topic> update(@RequestBody Topic topic){
        topicService.update(topic);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Topic> deleteById(@PathVariable UUID id){
        topicService.delete(id);
        return ResponseEntity.noContent().build() ;
    }

}
