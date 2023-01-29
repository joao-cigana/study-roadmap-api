package dev.cigana.coursesapi.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;

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

    @ManyToMany(mappedBy = "courses", fetch = FetchType.EAGER)
    private Set<Topic> topics;

    @ManyToOne
    @JoinColumn(name="platform_id", nullable=false)
    private Platform platform;

}
