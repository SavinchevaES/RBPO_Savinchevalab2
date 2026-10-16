package com.example.rbpo_savincheva.controller;

import com.example.rbpo_savincheva.service.NotFoundException;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(NotFoundException.class) public ResponseEntity<Map<String, String>> notFound(NotFoundException e) { return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", e.getMessage())); }
    @ExceptionHandler(IllegalArgumentException.class) public ResponseEntity<Map<String, String>> badRequest(IllegalArgumentException e) { return ResponseEntity.badRequest().body(Map.of("error", e.getMessage())); }
}
