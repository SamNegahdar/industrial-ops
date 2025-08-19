package com.ios.order.infrastructure.web;

import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestControllerAdvice
public class RestExceptionHandler {
  @ExceptionHandler(IllegalStateException.class)
  public ResponseEntity<Map<String, String>> illegalState(IllegalStateException ex) {
    return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("error", ex.getMessage()));
  }
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, String>> invalid(MethodArgumentNotValidException ex) {
    return ResponseEntity.badRequest().body(Map.of("error", "validation_failed"));
  }
}
