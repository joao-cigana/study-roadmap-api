package dev.cigana.coursesapi.services;

import dev.cigana.coursesapi.domain.Platform;
import dev.cigana.coursesapi.domain.dtos.PlatformFormDTO;
import dev.cigana.coursesapi.domain.exceptions.ResourceNotFoundException;
import dev.cigana.coursesapi.repositories.PlatformRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PlatformService {

    private static final String NOT_FOUND = "Platform not found - id: ";

    @Autowired
    private PlatformRepository platformRepository;

    public Page<Platform> findAll(Pageable pageable) {
        return platformRepository.findAll(pageable);
    }

    public Platform findById(UUID id) {
        return platformRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND + id));
    }

    public Platform create(PlatformFormDTO dto) {
        Platform platform = new Platform(dto);
        platform.setName(platform.getName().toUpperCase());
        platformRepository.save(platform);
        return platform;
    }

    public void update(Platform platform) {
        Platform updatedPlatform = platformRepository.findById(platform.getId())
                .orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND + platform.getId()));
        updatedPlatform.setName(platform.getName().toUpperCase());
        updatedPlatform.setPlatformLink(platform.getPlatformLink());
        updatedPlatform.setPlatformImageLink(platform.getPlatformImageLink());
        platformRepository.save(updatedPlatform);
    }

    public void delete(UUID id) {
        if (!platformRepository.existsById(id)) {
            throw new ResourceNotFoundException(NOT_FOUND + id);
        }
        platformRepository.deleteById(id);
    }

}
