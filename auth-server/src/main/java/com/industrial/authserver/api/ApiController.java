package com.industrial.authserver.api;

import com.industrial.common.ApiResponse;
import com.industrial.authserver.domain.Domain;
import com.industrial.authserver.service.ServiceLogic;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class ApiController {
  private final ServiceLogic logic = new ServiceLogic();

  @PostMapping("/token")
  public ApiResponse<Domain.TokenResponse> token(@Valid @RequestBody Domain.LoginRequest req){
    return ApiResponse.ok(logic.login(req));
  }
}
