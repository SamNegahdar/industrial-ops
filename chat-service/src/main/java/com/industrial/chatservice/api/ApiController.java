package com.industrial.chatservice.api;

import com.industrial.common.ApiResponse;
import com.industrial.chatservice.domain.Domain;
import com.industrial.chatservice.service.ServiceLogic;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
public class ApiController {
  private final ServiceLogic logic = new ServiceLogic();

  @PostMapping("/message")
  public ApiResponse<Domain.Message> post(@Valid @RequestBody Domain.Message m){
    return ApiResponse.ok(logic.post(m));
  }

  @GetMapping("/message")
  public ApiResponse<List<Domain.Message>> list(@RequestParam String channel){
    return ApiResponse.ok(logic.list(channel));
  }
}
