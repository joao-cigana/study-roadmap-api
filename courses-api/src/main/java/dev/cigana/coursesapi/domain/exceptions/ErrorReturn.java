package dev.cigana.coursesapi.domain.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ErrorReturn {

    private LocalDateTime moment;
    private String statusName;
    private int statusCode;
    private List<String> errorMessages = new ArrayList<>();

    public ErrorReturn(LocalDateTime moment, HttpStatus status, String message){
        this.moment = moment;
        this.statusName = status.name();
        this.statusCode = status.value();
        errorMessages.add(message);
    }

    public ErrorReturn(LocalDateTime moment, HttpStatus status, List<String> messages){
        this.moment = moment;
        this.statusName = status.name();
        this.statusCode = status.value();
        errorMessages.addAll(messages);
    }
}
