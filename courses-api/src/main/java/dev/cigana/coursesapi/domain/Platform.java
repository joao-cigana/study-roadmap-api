package dev.cigana.coursesapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.cigana.coursesapi.domain.dtos.PlatformFormDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "tb_platform")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
public class Platform {

    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String platformLink;

    private String platformImageLink;

    @OneToMany(mappedBy="platform")
    @JsonIgnore
    private Set<Course> courses = new HashSet<>();

    public Platform(PlatformFormDTO dto){
        this.name = dto.getName();
        this.platformLink = dto.getPlatformLink();
        this.platformImageLink = dto.getPlatformImageLink();
    }

}
