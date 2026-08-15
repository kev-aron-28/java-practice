package com.assessment.ticket_manager.Controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorController {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, String> map = new HashMap<>();

       ex.getBindingResult().getFieldErrors().forEach(e -> {
            map.put(e.getField(), e.getDefaultMessage());
       });

       return ResponseEntity.badRequest().body(map);
    }
}
