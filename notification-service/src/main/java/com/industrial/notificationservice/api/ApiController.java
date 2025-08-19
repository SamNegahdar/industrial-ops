package com.industrial.notificationservice.api;

import com.industrial.common.ApiResponse;
import com.industrial.notificationservice.domain.Domain;
import com.industrial.notificationservice.service.ServiceLogic;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notification")
public class ApiController {
  private final ServiceLogic logic = new ServiceLogic();

  @PostMapping
  public ApiResponse<Domain.Notification> send(@Valid @RequestBody Domain.Notification n){
    return ApiResponse.ok(logic.send(n));
  }

  @GetMapping
  public ApiResponse<List<Domain.Notification>> queued(){
    return ApiResponse.ok(logic.queued());
  }
}
