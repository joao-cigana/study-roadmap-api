package dev.cigana.coursesapi.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "tb_course")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
public class Course {

    private UUID id;

    private String name;

    private String courseLink;

    private LocalDateTime colclusionDate;

    private String certificateLink;

    @ManyToMany(mappedBy = "courses")
    private Set<Topic> topics;

    @ManyToOne
    @JoinColumn(name="platform_id", nullable=false)
    private Platform platform;

}
