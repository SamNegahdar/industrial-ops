package com.industrial.productionservice;
import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/ping")
public class PingController {
  @GetMapping public String ping() { return "production-service:ok"; }
}
