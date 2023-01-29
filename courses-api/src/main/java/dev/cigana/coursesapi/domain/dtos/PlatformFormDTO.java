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
public class PlatformFormDTO {

    @NotBlank(message = "Field 'name' must be informed")
    private String name;

    @NotBlank(message = "Field 'platform link' must be informed")
    private String platformLink;

    private String platformImageLink;

}
