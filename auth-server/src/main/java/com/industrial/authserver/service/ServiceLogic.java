package com.industrial.authserver.service;

import com.industrial.common.BusinessException;
import com.industrial.common.ErrorCode;
import com.industrial.authserver.domain.Domain;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class ServiceLogic {
  private static final String SECRET = "dev-secret-change-me";
  public Domain.TokenResponse login(Domain.LoginRequest req){
    // Super simple demo: username==password accepted
    if (!req.getUsername().equals(req.getPassword()))
      throw new BusinessException(ErrorCode.UNAUTHORIZED, "Invalid credentials");
    var token = Jwts.builder()
        .setSubject(req.getUsername())
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + 3600_000))
        .signWith(SignatureAlgorithm.HS256, SECRET.getBytes())
        .compact();
    return new Domain.TokenResponse(token);
  }
}
