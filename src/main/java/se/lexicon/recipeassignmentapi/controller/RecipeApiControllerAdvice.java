package se.lexicon.recipeassignmentapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import se.lexicon.recipeassignmentapi.model.dto.ValidationErrorResponseDto;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class RecipeApiControllerAdvice {

    public static final String VALIDATION_ERROR_MESSAGE = "En eller flera valideringar misslyckades";

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponseDto> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exception,
            WebRequest request
    ){
        ValidationErrorResponseDto errorResponseDto = new ValidationErrorResponseDto();
        errorResponseDto.setTimestamp(LocalDateTime.now());
        errorResponseDto.setError(HttpStatus.BAD_REQUEST.name());
        errorResponseDto.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponseDto.setMessage(VALIDATION_ERROR_MESSAGE);
        errorResponseDto.setPath(request.getDescription(false));

        List<String> fields = exception.getBindingResult().getFieldErrors().stream()
                .distinct()
                .map(FieldError::getField)
                .collect(Collectors.toList());

        Map<String, List<String>> validationErrors = new HashMap<>();

        for(String field : fields){
            List<String> list = new ArrayList<>();
            for(FieldError fieldError : exception.getFieldErrors(field)){
                list.add(fieldError.getDefaultMessage());
            }
            validationErrors.put(field, list);
        }
        errorResponseDto.setValidationErrors(validationErrors);

        return ResponseEntity.badRequest().body(errorResponseDto);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ValidationErrorResponseDto> handleConstraintViolationException(
            ConstraintViolationException exception,
            WebRequest request
    ){
        ValidationErrorResponseDto errorResponseDto = new ValidationErrorResponseDto();
        errorResponseDto.setTimestamp(LocalDateTime.now());
        errorResponseDto.setError(HttpStatus.BAD_REQUEST.name());
        errorResponseDto.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponseDto.setMessage(VALIDATION_ERROR_MESSAGE);
        errorResponseDto.setPath(request.getDescription(false));

        List<String> fields = exception.getConstraintViolations().stream()
                .map(constraintViolation -> constraintViolation.getPropertyPath().toString())
                .distinct()
                .collect(Collectors.toList());

        Map<String, List<String>> validationErrors = new HashMap<>();

        for(String field : fields){
            List<String> errors = exception.getConstraintViolations().stream()
                    .filter(constraintViolation -> constraintViolation.getPropertyPath().toString().equals(field))
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.toList());
            validationErrors.put(field, errors);
        }

        errorResponseDto.setValidationErrors(validationErrors);

        return ResponseEntity.badRequest().body(errorResponseDto);
    }

}
