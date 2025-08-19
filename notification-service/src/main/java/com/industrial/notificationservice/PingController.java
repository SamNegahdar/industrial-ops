package com.industrial.notificationservice;
import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/ping")
public class PingController {
  @GetMapping public String ping() { return "notification-service:ok"; }
}
