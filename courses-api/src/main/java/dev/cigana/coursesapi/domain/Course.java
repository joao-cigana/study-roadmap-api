package dev.cigana.coursesapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.cigana.coursesapi.domain.dtos.CourseFormDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.annotations.Fetch;

import java.time.LocalDateTime;
import java.util.HashSet;
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

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private String courseLink;

    private LocalDateTime conclusionDate;

    private String certificateLink;

    @ManyToMany
    @JoinTable(
            name = "course_topic",
            joinColumns = @JoinColumn(name = "topic_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private Set<Topic> topics = new HashSet<>();

    @ManyToOne
    @JoinColumn(name="platform_id", nullable=false)
    private Platform platform;

    public Course(CourseFormDTO dto){
        this.name = dto.getName();
        this.courseLink = dto.getCourseLink();
        this.certificateLink = dto.getCertificateLink();
        this.conclusionDate = dto.getConclusionDate();
        this.platform = dto.getPlatform();
        this.topics.addAll(dto.getTopics());
    }

}
