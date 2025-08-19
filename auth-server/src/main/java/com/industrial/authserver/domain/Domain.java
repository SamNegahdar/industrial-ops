package com.industrial.authserver.domain;

import jakarta.validation.constraints.NotBlank;

public class Domain {
  public static class LoginRequest {
    @NotBlank private String username;
    @NotBlank private String password;

    public String getUsername(){ return username; }
    public String getPassword(){ return password; }
    public void setUsername(String username){ this.username = username; }
    public void setPassword(String password){ this.password = password; }
  }
  public static class TokenResponse {
    private String token;
    public TokenResponse(){}
    public TokenResponse(String token){ this.token = token; }
    public String getToken(){ return token; }
    public void setToken(String token){ this.token = token; }
  }
}
