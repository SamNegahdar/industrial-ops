package com.industrial.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(BusinessException.class)
  public ResponseEntity<ApiResponse<Void>> handleBusiness(BusinessException ex) {
    HttpStatus status = HttpStatus.BAD_REQUEST;
    if (ex.getCode() == ErrorCode.NOT_FOUND) status = HttpStatus.NOT_FOUND;
    if (ex.getCode() == ErrorCode.UNAUTHORIZED) status = HttpStatus.UNAUTHORIZED;
    if (ex.getCode() == ErrorCode.FORBIDDEN) status = HttpStatus.FORBIDDEN;
    if (ex.getCode() == ErrorCode.CONFLICT) status = HttpStatus.CONFLICT;
    return ResponseEntity.status(status).body(ApiResponse.fail(ex.getCode(), ex.getMessage()));
  }

  @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
  public ResponseEntity<ApiResponse<Void>> handleValidation(Exception ex) {
    return ResponseEntity.badRequest().body(ApiResponse.fail(ErrorCode.VALIDATION_ERROR, ex.getMessage()));
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiResponse<Void>> handleGeneric(Exception ex) {
    return ResponseEntity.internalServerError().body(ApiResponse.fail(ErrorCode.INTERNAL_ERROR, ex.getMessage()));
  }
}
