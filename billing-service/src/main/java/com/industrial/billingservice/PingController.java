package com.industrial.billingservice;
import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/ping")
public class PingController {
  @GetMapping public String ping() { return "billing-service:ok"; }
}
