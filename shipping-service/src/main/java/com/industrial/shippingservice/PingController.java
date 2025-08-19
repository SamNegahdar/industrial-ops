package com.industrial.shippingservice;
import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/ping")
public class PingController {
  @GetMapping public String ping() { return "shipping-service:ok"; }
}
