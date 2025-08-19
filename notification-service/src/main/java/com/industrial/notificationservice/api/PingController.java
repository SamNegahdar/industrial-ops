package com.industrial.notificationservice.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingController {
  @GetMapping("/api/ping")
  public String ping() { return "notification-service:pong"; }
}
