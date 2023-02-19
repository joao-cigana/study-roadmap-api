package dev.cigana.coursesapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.cigana.coursesapi.domain.dtos.TopicFormDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "tb_topic")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(unique = true, nullable = false)
    private String name;

    private String imageLink;

    @JsonIgnore
    @ManyToMany(mappedBy = "topics", fetch = FetchType.EAGER)
    private Set<Course> courses = new HashSet<>();

    public Topic(TopicFormDTO dto){
        this.name = dto.getName();
        this.imageLink = dto.getImageLink();
    }

}
