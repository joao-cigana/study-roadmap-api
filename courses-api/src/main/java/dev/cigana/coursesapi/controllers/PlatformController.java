package dev.cigana.coursesapi.controllers;

import dev.cigana.coursesapi.domain.Platform;
import dev.cigana.coursesapi.domain.dtos.PlatformFormDTO;
import dev.cigana.coursesapi.services.PlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/platforms")
public class PlatformController {

    @Autowired
    private PlatformService platformService;

    @GetMapping
    public ResponseEntity<Page<Platform>> findAll(Pageable pageable){
        return ResponseEntity.ok(platformService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Platform> findById(@PathVariable UUID id){
        return ResponseEntity.ok(platformService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Platform> create(@RequestBody PlatformFormDTO dto){
        Platform newPlatform = platformService.create(dto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newPlatform.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping
    public ResponseEntity<Platform> update(@RequestBody Platform platform){
        platformService.update(platform);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Platform> deleteById(@PathVariable UUID id){
        platformService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
