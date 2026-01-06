package com.example.crudDemo.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<Map<String, Object>> handleValidationErrors(MethodArgumentNotValidException ex){

            Map<String, Object> errors = new HashMap<>();

            ex.getBindingResult().getFieldErrors()
                            .forEach(error ->
                                    errors.put(error.getField(), error.getDefaultMessage())
                            );

        Map<String, Object> response = new HashMap<>();


        response.put("timestamp", LocalDateTime.now());
            response.put("status", HttpStatus.BAD_REQUEST.value());
            response.put("error", errors);


            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
}
