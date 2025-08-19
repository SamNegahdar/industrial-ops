package com.industrial.billingservice.infrastructure;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.*;
import java.util.stream.Collectors;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class RestExceptionHandler {
  private final MessageSource messages;
  public RestExceptionHandler(MessageSource messages){ this.messages = messages; }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<?> handleValidation(MethodArgumentNotValidException ex, java.util.Locale locale){
    List<Map<String,String>> errs = ex.getBindingResult().getFieldErrors().stream().map(this::toMap).collect(Collectors.toList());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("errors", errs));
  }

  @ExceptionHandler(IllegalStateException.class)
  public ResponseEntity<?> handleIllegalState(IllegalStateException ex, java.util.Locale locale){
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", ex.getMessage()));
  }

  private Map<String,String> toMap(FieldError fe){
    return Map.of("field", fe.getField(), "message", fe.getDefaultMessage());
  }
}
