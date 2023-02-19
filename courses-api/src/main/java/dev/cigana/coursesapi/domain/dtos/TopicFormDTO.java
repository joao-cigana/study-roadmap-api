package dev.cigana.coursesapi.domain.dtos;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TopicFormDTO {

    @NotBlank(message = "Field 'name' must be informed.")
    private String name;

    private String imageLink;

}
