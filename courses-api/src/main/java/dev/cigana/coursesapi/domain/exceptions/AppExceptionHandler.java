package dev.cigana.coursesapi.domain.exceptions;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class AppExceptionHandler {

    private LocalDateTime now(){
        return LocalDateTime.now();
    }

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<ErrorReturn> resourceNotFound(ResourceNotFoundException notFound){
        HttpStatus status = HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(new ErrorReturn(now(), status , notFound.getMessage()));
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<ErrorReturn> constraintViolationException(ConstraintViolationException constraintViolation){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorReturn er =  new ErrorReturn(now(), status, constraintViolation.
                getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage).toList());
        return ResponseEntity.status(status).body(er);
    }

    @ExceptionHandler({org.hibernate.exception.ConstraintViolationException.class})
    public ResponseEntity<ErrorReturn> hibernateConstraintViolation(org.hibernate.exception.ConstraintViolationException validationViolation){
        String fullMessage = validationViolation.getCause().getMessage();
        String message = fullMessage.substring(fullMessage.lastIndexOf("Detalhe:"));
        return ResponseEntity.badRequest().body(new ErrorReturn(now(), HttpStatus.BAD_REQUEST,
                validationViolation.getMessage() + " " + message));
    }

    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public ResponseEntity<ErrorReturn> methodNotAllowed(HttpRequestMethodNotSupportedException methodNotAllowed){
        return ResponseEntity.badRequest().body(new ErrorReturn(now(), HttpStatus.BAD_REQUEST,
                 "Wrong path: " + methodNotAllowed.getMessage()));
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorReturn> argumentNotValid(MethodArgumentNotValidException argumentNotValid){
        return ResponseEntity.badRequest().body(new ErrorReturn(now(), HttpStatus.BAD_REQUEST,
                argumentNotValid.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList()));
    }


}
