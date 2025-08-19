package com.industrial.common;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
  private boolean success;
  private String code;
  private String message;
  private T data;

  public static <T> ApiResponse<T> ok(T data) {
    ApiResponse<T> r = new ApiResponse<>();
    r.success = true;
    r.code = ErrorCode.OK.name();
    r.data = data;
    return r;
  }
  public static ApiResponse<Void> ok() { return ok(null); }
  public static ApiResponse<Void> fail(ErrorCode code, String message) {
    ApiResponse<Void> r = new ApiResponse<>();
    r.success = false;
    r.code = code.name();
    r.message = message;
    return r;
  }
  public boolean isSuccess() { return success; }
  public String getCode() { return code; }
  public String getMessage() { return message; }
  public T getData() { return data; }
  public void setSuccess(boolean success) { this.success = success; }
  public void setCode(String code) { this.code = code; }
  public void setMessage(String message) { this.message = message; }
  public void setData(T data) { this.data = data; }
}
