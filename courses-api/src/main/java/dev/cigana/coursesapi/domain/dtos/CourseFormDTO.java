package dev.cigana.coursesapi.domain.dtos;

import dev.cigana.coursesapi.domain.CourseStatus;
import dev.cigana.coursesapi.domain.Platform;
import dev.cigana.coursesapi.domain.Topic;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseFormDTO {

    @NotBlank(message = "field 'name' must be informed.")
    private String name;

    @NotBlank(message = "field 'courseLink' must be informed.")
    private String courseLink;

    @NotNull(message = "field 'status' must be informed.")
    private CourseStatus status;

    private LocalDateTime conclusionDate;

    private String certificateLink;

    @NotEmpty(message = "add at least one topic.")
    private Set<Topic> topics = new HashSet<>();

    @NotNull(message = "field 'platform' must be informed.")
    private Platform platform;
}
