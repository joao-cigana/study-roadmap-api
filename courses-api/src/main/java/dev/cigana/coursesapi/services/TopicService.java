package dev.cigana.coursesapi.services;

import dev.cigana.coursesapi.domain.Topic;
import dev.cigana.coursesapi.domain.dtos.TopicFormDTO;
import dev.cigana.coursesapi.domain.exceptions.ResourceNotFoundException;
import dev.cigana.coursesapi.repositories.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TopicService {

    private static final String NOT_FOUND = "Topic not found - id: ";

    @Autowired
    private TopicRepository topicRepository;

    public Page<Topic> findAll(Pageable pageable){
        return topicRepository.findAll(pageable);
    }

    public Topic findById(UUID id){
        return topicRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND + id));
    }

    public Topic create(TopicFormDTO dto){
        Topic topic = new Topic(dto);
        topic.setName(topic.getName().toUpperCase());
        topicRepository.save(topic);
        return topic;
    }

    public void update(Topic topic){
        if(!topicRepository.existsById(topic.getId())){
            throw new ResourceNotFoundException(NOT_FOUND + topic.getId());
        }
        topicRepository.save(topic);
    }

    public void delete(UUID id){
        if(!topicRepository.existsById(id)){
            throw new ResourceNotFoundException(NOT_FOUND + id);
        }
        topicRepository.deleteById(id);
    }

}
